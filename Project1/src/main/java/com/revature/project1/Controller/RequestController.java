package com.revature.project1.Controller;

import com.revature.project1.Controller.Service.RequestService;
import io.javalin.Javalin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

import java.util.List;

    public class RequestController {

        RequestService requestService;
        public RequestController(){
            requestService = new RequestService();
        }
        public void requestEndpoint(Javalin app){

        /*
        app.[http verb]([url endpoint after localhost:8080], this::[handler method]);

        http verbs:
        get (retrieve some representations)
        post (persist some representations that is contained within a body)
        put (update a model representation)
        patch (update a part of a representation)
        delete (delete some representation)

        url endpoint: ex, localhost:8080/endpoint

        handler method: a method we write in this class which will be passed the Javalin context for us to use,
        which can hold information about the web request that was made, and can also generate a response.
         */
            app.get("helloMenu", this::helloHandler);
            app.post("menuItem",this::postMenuItemHandler);
            app.get("menuItem", this::getAllMenuItemsHandler);
            app.get("menuItem/{name}",this::getSpecificCustomerHandler);
        }

        /**
         * use the javalin context to retrieve a String from the path.
         * use the menuItemService to retrieve a specific MenuItem and return it as JSON.
         * @param context
         */
        private void getSpecificCustomerHandler(Context context) {
            String name = context.pathParam("name");
            MenuItem menuItem = menuItemService.getMenuItem(name);
            context.json(menuItem);
        }

        /**
         * use the javalin context to return a JSON representation of the list of all MenuItems which
         * we have received from the MenuItemService.
         * @param context
         */
        private void getAllMenuItemsHandler(Context context) {
            List<MenuItem> allMenuItems = menuItemService.getAllMenuItems();
//        similar as context.result, but the content type is json rather than text.
            context.json(allMenuItems);
        }

        /**
         * receive a JSON representation of a MenuItem in the body of an HTTP request.
         * that representation should be converted into a Java object and saved to the MenuItemService.
         * @param context
         */
        private void postMenuItemHandler(Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            MenuItem menuItem = mapper.readValue(context.body(), MenuItem.class);
            menuItemService.addCustomer(menuItem);
            context.json(menuItem);
        }

        public void helloHandler(Context ctx){
            ctx.result("hello from the menu");
        }
    }
