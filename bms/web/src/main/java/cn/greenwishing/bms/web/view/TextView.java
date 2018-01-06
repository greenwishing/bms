package cn.greenwishing.bms.web.view;

import cn.greenwishing.bms.utils.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * User: WuFan
 * Date: 2016-10-18
 */
public class TextView extends AbstractView {

    public static final String CONTENT_TYPE = "text/plain";

    private String filename;
    private String content;

    public TextView(String filename, String content) {
        setContentType(CONTENT_TYPE);
        this.filename = filename;
        this.content = content;
    }

    /**
     * Subclasses must implement this method to actually render the view.
     * <p>The first step will be preparing the request: In the JSP case,
     * this would mean setting model objects as request attributes.
     * The second step will be the actual rendering of the view,
     * for example including the JSP via a RequestDispatcher.
     *
     * @param model    combined output Map (never <code>null</code>),
     *                 with dynamic values taking precedence over static attributes
     * @param request  current HTTP request
     * @param response current HTTP response
     * @throws Exception if rendering failed
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + StringUtils.encode(filename) + ".txt\"");
        ServletOutputStream os = response.getOutputStream();
        os.print(content);
        os.close();
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
