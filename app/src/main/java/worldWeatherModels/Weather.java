package worldWeatherModels;

import java.util.List;

final public class Weather {

    private final String date;
    private final List<Astronomy> astronomy;
    private final String maxtempC;
    private final String maxtempF;
    private final String mintempC;
    private final String mintempF;
    private final String uvIndex;
    private final List<Hour> hourly;

    public Weather(String date, List<Astronomy> astronomy, String maxtempC, String maxtempF, String mintempC, String mintempF, String uvIndex, List<Hour> hourly) {
        this.date = date;
        this.astronomy = astronomy;
        this.maxtempC = maxtempC;
        this.maxtempF = maxtempF;
        this.mintempC = mintempC;
        this.mintempF = mintempF;
        this.uvIndex = uvIndex;
        this.hourly = hourly;
    }

    public String getDate() {
        return date;
    }

    public List<Astronomy> getAstronomy() {
        return astronomy;
    }

    public String getMaxtempC() {
        return maxtempC;
    }

    public String getMaxtempF() {
        return maxtempF;
    }

    public String getMintempC() {
        return mintempC;
    }

    public String getMintempF() {
        return mintempF;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public List<Hour> getHourly() {
        return hourly;
    }
}
