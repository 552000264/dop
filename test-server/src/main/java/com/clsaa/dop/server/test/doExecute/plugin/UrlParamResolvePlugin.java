package com.clsaa.dop.server.test.doExecute.plugin;

import com.clsaa.dop.server.test.doExecute.Version;
import com.clsaa.dop.server.test.doExecute.context.RequestContext;
import com.clsaa.dop.server.test.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xihao
 * @version 1.0
 * @since 12/04/2019
 */
@Component
@Slf4j
public class UrlParamResolvePlugin implements UrlPlugin {

    @Override
    public void apply(RequestContext requestContext) {
        String url = requestContext.getUrl();
        Map<String, String> params = requestContext.getParams();
        //update url
        requestContext.setUrl(StringUtils.tryToResolve(url, params));
    }

    @Override
    public boolean supports(Version delimiter) {
        return delimiter == Version.V1;
    }

}
