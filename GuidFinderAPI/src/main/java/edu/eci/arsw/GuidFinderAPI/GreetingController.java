/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.GuidFinderAPI;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 2108263
 */

@RestController
public class GreetingController {



    @RequestMapping("/uuid")
    public Greeting greeting(@RequestParam(value="name") String name) {
        return new Greeting();
    }
}