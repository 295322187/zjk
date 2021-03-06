package com.zjk.module.common.authorization.server.api.businesscode.biz.impl;

import com.alibaba.fastjson.JSON;
import com.zjk.module.common.authorization.client.api.businesscode.biz.IBusinessCodeRegisterService;
import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCode;
import com.zjk.module.common.authorization.client.api.businesscode.domain.BusinessCodeLang;
import com.zjk.module.common.authorization.server.api.businesscode.biz.IBusinessCodeService;
import com.zjk.module.common.authorization.server.base.businesscode.biz.ITCBusinessCodeService;
import com.zjk.module.common.authorization.server.base.businesscode.domain.TCBusinessCode;
import com.zjk.module.common.authorization.server.base.businesscodelang.biz.ITCBusinessCodeLangService;
import com.zjk.module.common.authorization.server.base.businesscodelang.domain.TCBusinessCodeLang;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.redis.biz.IRedisService;
import com.zjk.module.common.redis.constant.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessCodeServiceImpl extends CommonServiceImpl implements IBusinessCodeRegisterService, IBusinessCodeService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ITCBusinessCodeService businessCodeService;
	@Autowired
	private ITCBusinessCodeLangService businessCodeLangService;

	/**
	 * 更新
	 */
	private static final String UPDATE = "update";
	/**
	 * 新增
	 */
	private static final String SAVE = "save";

	@Override
	@Transactional
	public List<BusinessCode> register(List<BusinessCode> vos) {
		vos.forEach(e -> {
			TCBusinessCode po = businessCodeService.findOneByBusinessClazzAndBusinessCode(e.getBusinessClazz(), e.getBusinessCode());
			if (null == po) {
				// 不存在新增
				po = businessCodeService.newInstance();
				e.setOperate(SAVE);
			} else {
				// 存在更新
				e.setOperate(UPDATE);
			}
			e.setCode(po.getCode());

			po.setBusinessClazz(e.getBusinessClazz());
			po.setBusinessCode(e.getBusinessCode());
			po.setMessage(e.getMessage());
			po.setCustomerMessage(e.getCustomerMessage());
			businessCodeService.save(po);

			// 多语言表只新增不更新
			e.getBusinessCodeLangs().forEach(s -> {
				String lang = s.getLang().toLowerCase();
				TCBusinessCodeLang langPo = businessCodeLangService.findOneByCodeAndLang(e.getCode(), lang);
				if (null == langPo) {
					langPo = new TCBusinessCodeLang();
					langPo.setCode(e.getCode());
					// 存小写
					langPo.setLang(lang);
					langPo.setCustomerMessage(s.getCustomerMessage());
					businessCodeLangService.save(langPo);
				}
			});
		});
		return vos;
	}

	private BusinessCode convert(TCBusinessCode e) {
		BusinessCode vo = new BusinessCode(e.getCode(), e.getBusinessClazz(), e.getBusinessCode(), e.getMessage(), e.getCustomerMessage());
		List<BusinessCodeLang> langVos = map(businessCodeLangService.findAllByCode(e.getCode()), s -> new BusinessCodeLang(s.getLang(), s.getCustomerMessage()));
		vo.setBusinessCodeLangs(langVos);
		return vo;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BusinessCode> findAll(Pageable pageable) {
		return businessCodeService.findAll(pageable).map(e -> convert(e));
	}

	private String getKey(String businessClazz, String businessCode) {
		return RedisConstant.getKey(BusinessCode.class, businessClazz, businessCode);
	}

	@Override
	@Scheduled(cron = "${sync.cron.businesscode}")
	public void refresh() {
		List<BusinessCode> vos = map(businessCodeService.findAll(), e -> convert(e));
		// 缓存业务代码列表
		for (BusinessCode vo : vos) {
			redisService.set2RedisTwoDays(getKey(vo.getBusinessClazz(), vo.getBusinessCode()), JSON.toJSONString(vo));
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode) {
		BusinessCode vo = null;
		String valuer = (String) redisService.get(getKey(businessClazz, businessCode));
		if (StringUtils.isNotBlank(valuer)) {
			vo = JSON.parseObject(valuer, BusinessCode.class);
		}
		return vo;
	}

	@Override
	@Transactional
	public void save(BusinessCode vo) {
		businessCodeLangService.save(map(vo.getBusinessCodeLangs(), e -> {
			TCBusinessCodeLang po = businessCodeLangService.findOneByCodeAndLang(vo.getCode(), e.getLang());
			if (null == po) {
				po = new TCBusinessCodeLang(vo.getCode(), e.getLang());
			}
			po.setCustomerMessage(e.getCustomerMessage());
			return po;
		}));
	}
}
