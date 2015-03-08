/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 *
 * @author wlieurance
 */
@WebListener
public class EuclidListener implements ServletContextListener {
    private static String ascii = "\n"
+"         __                     " +"\n"
+"        / /\\                   " +"\n"
+"       / /  \\                 " +"\n"
+"      / /    \\__________      " +"\n"
+"     / /      \\        /\\       " +"\n"
+"    /_/        \\      / /      " +"\n"
+" ___\\ \\      ___\\____/_/_     " +"\n"
+"/____\\ \\    /___________/\\   " +"\n"
+"\\     \\ \\   \\           \\ \\    " +"\n"
+" \\     \\ \\   \\____       \\ \\    " +"\n"
+"  \\     \\ \\  /   /\\       \\ \\   " +"\n"
+"   \\   / \\_\\/   / /        \\ \\  " +"\n"
+"    \\ /        / /__________\\/ " +"\n"
+"     /        / /     /       " +"\n"
+"    /        / /     /       " +"\n"
+"   /________/ /\\    /       " +"\n"
+"   \\________\\/\\ \\  /       " +"\n"
+"               \\_\\/" +"\n"
+"" +"\n";




    //This seems like a stupid way to do this.
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        long start, end, delta;
        start =  System.currentTimeMillis();
        Logger.getLogger(EuclidListener.class.getName()).log(Level.INFO, "Injesting initial data...");
        sce.getServletContext().setAttribute("EuclidManager", new EuclidManager());
        end = System.currentTimeMillis();
        delta = end - start;
        
        Logger.getLogger(EuclidListener.class.getName()).log(Level.INFO, ascii + "Startup took {0}ms", delta);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
