package com.rrtyui.weatherapplication.controller.weather;

import com.rrtyui.weatherapplication.dto.LocationSearchDto;
import com.rrtyui.weatherapplication.service.weather.IndianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class IndianController {
    private final IndianService indianService;

    @Autowired
    public IndianController(IndianService indianService) {
        this.indianService = indianService;
    }

    @GetMapping(value = "/byCity", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LocationSearchDto> getSearchResults(@RequestParam("city") String city) {
        return indianService.vernutVse(city);
    }
}
