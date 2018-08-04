package com.neuedu.crm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.DictionaryItem;
import com.neuedu.crm.pojo.DictionaryType;
import com.neuedu.crm.pojo.DictionaryTypeExample;
import com.neuedu.crm.pojo.Report;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.IDictionaryTypeService;
import com.neuedu.crm.service.IReportService;
import com.neuedu.crm.utils.Operation;

/**
 * 报表模块
 * 
 * @author admin
 *
 */
@Operation(name = "报表控制器")
@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IReportService reportService;

	@Autowired
	private IDictionaryTypeService dictTypeService;

	/**
	 * 
	 * 描述：统计公司新增的客户量 近day天的新增量
	 * 
	 * @author huangqingwen
	 * @version 1.0
	 * @param day
	 * @param
	 * @return
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name = "统计公司新增的客户量")
	@RequestMapping("/countCustomer")
	@ResponseBody
	public Map<String, Object> countCustomerIncrease(int day) {

		System.err.println(day + "+++++++++++++");

		Map<String, Object> maps = new HashMap<String, Object>(16);

		// 客户新增量
		List<Map<String, Object>> increase = reportService.countCustomerIncrease(day);

		// 客户损失量
		List<Map<String, Object>> decrease = reportService.countCustomerDecrease(day);

		maps.put("increase", increase);
		maps.put("decrease", decrease);
		maps.put("code", 0);
		return maps;
	}

	@Operation(name = "统计客户经理新增的客户与跟进")
	@RequiresPermissions("16001")
	@RequestMapping("/countCustomerByUser")
	@ResponseBody
	public Map<String, Object> countByDate(String action, Integer selectType, String date, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>(16);
		List<Map<String, Object>> list = null;
		if (action == null || selectType == null || date == null) {
			map.put("msg", "非法操作");
			map.put("code", -1);
			return map;
		}

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String dictName = "";
		String dateStr = "";

		switch (action) {
		case "year":

			// 检验时间输入
			try {
				dateStr = date.substring(0, 4);
			} catch (Exception e) {
				map.put("msg", "时间格式错误，输入格式样例：2018");
				map.put("code", -100);
				return map;
			}

			switch (selectType) {
			case 1010:
				list = reportService.countCustomerByYear(dateStr, user.getId());
				break;
			case 1020:
				list = reportService.countFollowUpByYear(date, user.getId());
				break;
			case 2010:
				list = reportService.countCustomerByCategoryWithYear(Report.CATEGORY_CREDIT, date, user.getId());
				dictName = "客户信用度";
				break;
			case 2020:
				list = reportService.countCustomerByCategoryWithYear(Report.CATEGORY_LEVEL, date, user.getId());
				dictName = "客户等级";
				break;
			case 2030:
				list = reportService.countCustomerByCategoryWithYear(Report.CATEGORY_MATURITY, date, user.getId());
				dictName = "客户成熟度";
				break;
			case 2040:
				list = reportService.countCustomerByCategoryWithYear(Report.CATEGORY_SOURCE, date, user.getId());
				dictName = "客户来源";
				break;
			case 2050:
				list = reportService.countCustomerByCategoryWithYear(Report.CATEGORY_STATUS, date, user.getId());
				dictName = "客户状态";
				break;
			case 2060:
				list = reportService.countCustomerByCategoryWithYear(Report.CATEGORY_TYPE, date, user.getId());
				dictName = "客户类别";
				break;
			default:
				map.put("msg", "非法操作");
				map.put("code", -3);
				return map;
			}
			break;

		case "month":

			// 检验时间输入
			try {
				dateStr = date.substring(0, 7);
			} catch (Exception e) {
				map.put("msg", "时间格式错误，输入格式样例：2018-02");
				map.put("code", -200);
				return map;
			}

			switch (selectType) {
			case 1010:
				list = reportService.countCustomerByMonth(date, user.getId());

				break;
			case 1020:
				list = reportService.countFollowUpByMonth(date, user.getId());

				break;
			case 2010:
				list = reportService.countCustomerByCategoryWithMonth(Report.CATEGORY_CREDIT, date, user.getId());
				dictName = "客户信用度";
				break;
			case 2020:
				list = reportService.countCustomerByCategoryWithMonth(Report.CATEGORY_LEVEL, date, user.getId());
				dictName = "客户等级";
				break;
			case 2030:
				list = reportService.countCustomerByCategoryWithMonth(Report.CATEGORY_MATURITY, date, user.getId());
				dictName = "客户成熟度";
				break;
			case 2040:
				list = reportService.countCustomerByCategoryWithMonth(Report.CATEGORY_SOURCE, date, user.getId());
				dictName = "客户来源";
				break;
			case 2050:
				list = reportService.countCustomerByCategoryWithMonth(Report.CATEGORY_STATUS, date, user.getId());
				dictName = "客户状态";
				break;
			case 2060:
				list = reportService.countCustomerByCategoryWithMonth(Report.CATEGORY_TYPE, date, user.getId());
				dictName = "客户类别";
				break;
			default:
				map.put("msg", "非法操作");
				map.put("code", -3);
				return map;
			}
			break;
		case "day":

			// 检验时间输入
			try {
				dateStr = date.substring(0, 10);
			} catch (Exception e) {
				map.put("msg", "时间格式错误，输入格式样例：2018-02-01");
				map.put("code", -300);
				return map;
			}

			switch (selectType) {
			case 1010:
				list = reportService.countCustomerByDay(date, user.getId());
				break;
			case 1020:
				list = reportService.countFollowUpByDay(date, user.getId());
				break;
			case 2010:
				list = reportService.countCustomerByCategoryWithDay(Report.CATEGORY_CREDIT, date, user.getId());
				dictName = "客户信用度";
				break;
			case 2020:
				list = reportService.countCustomerByCategoryWithDay(Report.CATEGORY_LEVEL, date, user.getId());
				dictName = "客户等级";
				break;
			case 2030:
				list = reportService.countCustomerByCategoryWithDay(Report.CATEGORY_MATURITY, date, user.getId());
				dictName = "客户成熟度";
				break;
			case 2040:
				list = reportService.countCustomerByCategoryWithDay(Report.CATEGORY_SOURCE, date, user.getId());
				dictName = "客户来源";
				break;
			case 2050:
				list = reportService.countCustomerByCategoryWithDay(Report.CATEGORY_STATUS, date, user.getId());
				dictName = "客户状态";
				break;
			case 2060:
				list = reportService.countCustomerByCategoryWithDay(Report.CATEGORY_TYPE, date, user.getId());
				dictName = "客户类别";
				break;
			default:
				map.put("msg", "非法操作");
				map.put("code", -3);
				return map;
			}
			break;

		default:
			map.put("msg", "非法操作");
			map.put("code", -2);
			return map;
		}

		// 进行数据补充
		int categoryCode = 2000;
		if (selectType > categoryCode) {
			// 填充空的数据
			DictionaryTypeExample example = new DictionaryTypeExample();
			example.createCriteria().andNameEqualTo(dictName);
			List<DictionaryType> dictionaryTypes = dictTypeService.selectByDictionaryTypeExample(example);
			DictionaryType dict = null;
			if (dictionaryTypes.size() > 0) {
				dict = dictionaryTypes.get(0);
			} else {
				dict = new DictionaryType();
				System.out.println("dict is null " + dictName);
			}
			if (list == null) {
				list = new ArrayList<Map<String, Object>>();
			}

			List<String> nameList = new ArrayList<String>();
			for (Map<String, Object> m : list) {
				nameList.add((String) m.get("category"));
			}

			List<DictionaryItem> dictionaryItems = dict.getDictionaryItems();
			if (dictionaryItems == null) {
				dictionaryItems = new ArrayList<DictionaryItem>();
				System.out.println("items is null");
			}

			for (DictionaryItem item : dictionaryItems) {
				if (!nameList.contains(item.getName())) {
					Map<String, Object> data = new HashMap<String, Object>(16);
					data.put("category", item.getName());
					data.put("count", 0);
					list.add(data);
				}
			}
		}

		map.put("data", list);
		map.put("code", 0);
		return map;
	}
	
	/**
	 * 
	 * 描述：统计某个客户经理的服务情况
	 * @author wanghaoyu
	 * @version 1.0
	 * @param sCategory
	 * @param day
	 * @param userId
	 * @return 
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "统计客户经理处理的服务")
	@RequiresPermissions("11001")
	@RequestMapping("/countManagerService")
	@ResponseBody
	public Map<String, Object> countManagerService(String sCategory, Integer day, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		if (day == null || day.equals(0)) {
			day = 7;
		}
		List<Map<String, Object>> services = reportService.countManagerService(sCategory, day, userId);
		map.put("data", services);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * 描述：统计全部客户经理的服务情况
	 * @author wanghaoyu
	 * @version 1.0
	 * @param sCategory
	 * @param day
	 * @return 
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "统计全部客户经理的服务")
	@RequiresPermissions("12001")
	@RequestMapping("/countAllManagerService")
	@ResponseBody
	public Map<String, Object> countAllManagerService(String sCategory, Integer day) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		if (day == null || day.equals(0)) {
			day = 7;
		}
		Map<String, Object> services = reportService.countAllManagerService(sCategory, day);
		map.put("data", services);
		map.put("code", 0);
		return map;
	}

	/**
	 * 统计客户经理的客户量排名
	 * 
	 * @param report
	 * @return
	 * @author huangqingwen
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name = "统计客户经理的客户量排名")
	@RequestMapping("/countManagerCustomerRank")
	@ResponseBody
	public Map<String, Object> countManagerCustomerRank(
			@RequestParam(value = "startTime", defaultValue = "") String startTime,
			@RequestParam(value = "endTime", defaultValue = "") String endTime) {
		Map<String, Object> maps = new HashMap<String, Object>(16);
		Report report = new Report();
		try {
			if (!"".equals(startTime)) {
				report.setStartTime(new SimpleDateFormat("yy-MM-dd").parse(startTime));
			}
			if (!"".equals(endTime)) {
				report.setEndTime(new SimpleDateFormat("yy-MM-dd").parse(endTime));
			}

			List<Map<String, Object>> result = reportService.countManagerCustomerRank(report);

			maps.put("data", result);
			maps.put("code", 0);
		} catch (Exception e) {
			maps.put("code", 200);
			maps.put("msg", "日期格式有误!");
		}

		return maps;
	}

	/**
	 * 统计客户订单排行
	 * 
	 * @param report
	 * @return
	 * @author huangqingwen
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name = "统计客户订单排行")
	@RequestMapping("/customerOrderPriceRank")
	@ResponseBody
	public Map<String, Object> customerOrderPriceRank(
			@RequestParam(value = "startTime", defaultValue = "") String startTime,
			@RequestParam(value = "endTime", defaultValue = "") String endTime) {

		Map<String, Object> maps = new HashMap<String, Object>(16);

		Report report = new Report();
		try {
			if (!"".equals(startTime)) {
				report.setStartTime(new SimpleDateFormat("yy-MM-dd").parse(startTime));
			}
			if (!"".equals(endTime)) {
				report.setEndTime(new SimpleDateFormat("yy-MM-dd").parse(endTime));
			}
			List<Map<String, Object>> result = reportService.customerOrderPriceRank(report);
			maps.put("data", result);
			maps.put("code", 0);
		}catch(Exception e){
			maps.put("code", 200);
			maps.put("msg", "时间格式有误!");
		}
		return maps;
	}
	
	/**
	 * 
	    * @Title: countCustomerSource
	    * @Description: TODO(统计公司客户的来源情况)
	    * @param @return    参数
	    * @return Map<String,Object>    返回类型
	    * @throws
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name="统计公司的客户来源情况")
	@RequestMapping("/countCustomerSource")
	@ResponseBody
	public Map<String, Object> countCustomerSource(){
		
		Map<String, Object> maps = new HashMap<String, Object>(16);
		
		List<Map<String, Object>> result = reportService.countCustomerSource();
		
		maps.put("data", result);
		maps.put("code", 0);
		
		return maps;
	}
}
