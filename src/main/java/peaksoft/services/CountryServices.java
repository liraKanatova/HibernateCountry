package peaksoft.services;

import peaksoft.model.Country;

import java.util.List;

public interface CountryServices {
    String saveCountry(Country country);
    String saveAllCountry(List<Country> countries);
    List<Country> getAllCountry();
    Country findByCountryId(Long id);
    String deleteCountryById(Long id);
    void deleteAllCounty();
    List<Country> findTheLongestDescription();
    Country updateCountry(Long id,Country country);
    int CountryProgrammerInCountry(peaksoft.config.enums.Country countryName);

}
