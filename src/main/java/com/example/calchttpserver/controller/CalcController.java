package com.example.calchttpserver.controller;

import com.example.calchttpserver.data.CalcResultData;
import com.example.calchttpserver.database.DatabaseController;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
public class CalcController {
    @GetMapping("/")
    public String index() {
        return "<html>Q_Q</html>";
    }

    @RequestMapping(value="/calc", method = RequestMethod.GET)
    public @ResponseBody String calc(
            @RequestParam("a") int a,
            @RequestParam("b") int b)
            throws SQLException
    {
        StringBuilder responseString = new StringBuilder();
        CalcResultData resultData = new CalcResultData(a, b, "+", a + b);
        DatabaseController dbInstance = DatabaseController.getInstance();

        dbInstance.addLog(resultData);

        responseString
            .append("<html>")
            .append("A + B = ")
            .append(resultData.getResult())
            .append("</html>");

        return responseString.toString();
    }
}
