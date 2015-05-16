package controllers.swagger_ui;

import play.mvc.Controller;

/**
 * @author Michael Ruf
 * @since 2015-05-05
 */
@SuppressWarnings("unused")
public class SwaggerUIController extends Controller {

    public static void index() {
        render("swagger_ui/index.html");
    }

    public static void o2c() {
        render("swagger_ui/o2c.html");
    }

}
