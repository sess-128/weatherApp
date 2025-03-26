    package com.rrtyui.weatherapplication.service.weather;

    import com.rrtyui.weatherapplication.dto.LocationSearchDto;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.util.List;

    @Service
    public class IndianService {
        private final RestTemplate restTemplate;

        @Autowired
        public IndianService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public List<LocationSearchDto> vernutVse(String city) {
            String url = "https://api.openweathermap.org/geo/1.0/direct?q={city}&limit=5&appid=c868cca3f789b0d16792b9b8db92507c";
            url = url.replace("{city}", city);

            System.out.println("URL is: " + url);

            ResponseEntity<LocationSearchDto[]> response = restTemplate.getForEntity(url, LocationSearchDto[].class);
            System.out.println("Response status: " + response.getStatusCode());

            LocationSearchDto[] body = response.getBody();

            if (body == null) {
                return List.of(); // Возвращаем пустой список, если ответ пустой
            }

            // Выводим информацию о каждом городе
            for (LocationSearchDto location : body) {
                System.out.println("City: " + location.getCity());
                System.out.println("Country: " + location.getCountry());
                System.out.println("State: " + location.getState());
                System.out.println("Lat: " + location.getLatitude());
                System.out.println("Lon: " + location.getLongitude());
            }

            return List.of(body); // Возвращаем список городов
        }
    }
