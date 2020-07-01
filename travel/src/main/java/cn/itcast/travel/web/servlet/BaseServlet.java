package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *   这个就是分发方法的servlet
 */
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取请求的路径(获取的是相对路径)
        String uri = request.getRequestURI();
        //System.out.println(uri);   //    /travel/user/add
        // 2. 获取请求方法名称
        String methodName = uri.substring(uri.lastIndexOf('/')+1);
        //System.out.println(methodName);  //   add
        // 3. 获取方法的执行对象   ---> 谁调用，对象就是谁
        //System.out.println(this);   // 对象就是UserServlet
        try {
            // 3.1 获取方法
            /*
                在这里我们知道，通过反射，我们可以获取调用方法对象的字节码文件。
                这样我们可以通过，方法名来获取方法
             */
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);

            // 3.2 执行方法
            /*
                   如果方法使用protect方法修饰，那么休要暴力反射
                   如果方法使用public方法修饰，直接就可以用invoke方法
             */
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


    /**
     * 返回一个json对象的字符串
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);

    }

    /**
     * 把obj对象序列化为json对象，并直接写回
     * @param obj
     * @param resq
     * @throws IOException
     */
    public void writeValue(Object obj, HttpServletResponse resq) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resq.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resq.getOutputStream(),obj);
    }

}
