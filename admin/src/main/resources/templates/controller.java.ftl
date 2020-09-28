package ${package.Controller};

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.wxh.util.ParamUtil;
import com.wxh.util.Result;
import com.wxh.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
	
	@Autowired
	private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};

	@RequestMapping("/edit")
	public Result edit(@RequestBody ${entity} ${entity?uncap_first}) {
		${(table.serviceName?substring(1))?uncap_first}.saveOrUpdate(${entity?uncap_first});
		return ResultUtil.getInfo("操作成功");
	}

	@RequestMapping("/delete")
	public Result delete(String id) {
		${(table.serviceName?substring(1))?uncap_first}.removeById(id);
		return ResultUtil.getInfo("操作成功");
	}

	@RequestMapping("/list")
	public Result list(@RequestBody ${entity} ${entity?uncap_first}) throws Exception {
		List<${entity}> result = ${(table.serviceName?substring(1))?uncap_first}.list(new ParamUtil<${entity}>().getQueryWrapper(${entity?uncap_first}));
		return ResultUtil.getData(result);
	}

	@RequestMapping("/page")
	public Result page(@RequestBody ${entity} ${entity?uncap_first}, Long current, Long size) throws Exception {
		IPage<${entity}> result = ${(table.serviceName?substring(1))?uncap_first}.page(new Page<${entity}>(${entity?uncap_first}.getCurrentPage(), ${entity?uncap_first}.getPageSize()),
				new ParamUtil<${entity}>().getQueryWrapper(${entity?uncap_first}));
		return ResultUtil.getData(result);
	}
	
}
</#if>
