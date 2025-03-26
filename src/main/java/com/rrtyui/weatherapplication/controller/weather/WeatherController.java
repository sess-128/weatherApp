package com.rrtyui.weatherapplication.controller.weather;

import com.rrtyui.weatherapplication.dto.LocationSearchDto;
import com.rrtyui.weatherapplication.service.weather.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search-results")
public class WeatherController {
    private final OpenWeatherMapService openWeatherMapService;

    @Autowired
    public WeatherController(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @GetMapping
    public String search(@RequestParam(value = "city", required = false) String city,
            Model model) {
        
            List<LocationSearchDto> cities = openWeatherMapService.findAll(city);
            model.addAttribute("cities", cities);

        return "search-results";
    }
}