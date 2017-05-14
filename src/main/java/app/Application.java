package app;


import app.util.Path;
import app.util.ViewUtil;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Application {

    public static void main(String[] args) {

        Map<String, Object> model = new HashMap<>();

        // Configure Spark
        port(4567);
        staticFiles.location("/public");

        get("/home", (request, response) -> {


            model.put("connected", false);

            return ViewUtil.render(request, model, Path.Template.BLANK);
        });

        get("/connect", (request, response) -> {


            model.put("connected", true);

            for (String s : request.queryParams()) {

                model.put(s, request.queryParams(s));

            }


            model.put("tables", JDBCConnectionManager.getInstance().getTableNames(request.queryParams("driver"), request.queryParams("url"), request.queryParams("user"), request.queryParams("password")));


            //  System.out.println("o que veio na bagaça: " + request.queryParams());

            return ViewUtil.render(request, model, Path.Template.BLANK);
        });

        get("/table", (request, response) -> {


            model.put("connected", true);

            model.put("table", request.queryParams("selected-table"));

            model.put("fields", JDBCConnectionManager.getInstance().getFileds(JDBCConnectionManager.getConnection(), request.queryParams("selected-table")));


            //  System.out.println("o que veio na bagaça: " + request.queryParams());

            return ViewUtil.render(request, model, Path.Template.STEP2);
        });

        get("/settings", (request, response) -> {


            return ViewUtil.render(request, model, Path.Template.STEP3);
        });

        post("/generate", (request, response) -> {

            model.put("dir", request.queryParams("dir"));

            model.put("code", new VelocityTemplateEngine().render(new ModelAndView(model, "/velocity/ballerina-dss.vm")));


            return ViewUtil.render(request, model, Path.Template.GENERATE);
        });


    }

}
