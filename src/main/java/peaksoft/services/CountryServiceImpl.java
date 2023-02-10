package peaksoft.services;

import peaksoft.model.Country;
import peaksoft.repositories.CountryRepository;
import peaksoft.repositories.CountryRepositoryImpl;

import java.util.List;

public class CountryServiceImpl implements CountryServices{
    CountryRepository countryRepository = new CountryRepositoryImpl();

    @Override
    public String saveCountry(Country country) {
        return countryRepository.saveCountry(country);
    }

    @Override
    public String saveAllCountry(List<Country> countries) {
        countryRepository.saveAllCountry(countries);
        return "Successfully saved all!";
    }

    @Override
    public List<Country> getAllCountry() {
        return countryRepository.getAllCountry();
    }

    @Override
    public Country findByCountryId(Long id) {
        return countryRepository.findByCountryId(id);
    }

    @Override
    public String deleteCountryById(Long id) {
        countryRepository.deleteCountryById(id);
        return "successfully deleted!" ;
    }

    @Override
    public void deleteAllCounty() {
        countryRepository.deleteAllCounty();

    }

    @Override
    public List<Country> findTheLongestDescription() {
        return countryRepository.findTheLongestDescription();
    }

    @Override
    public Country updateCountry(Long id, Country country) {
        return countryRepository.updateCountry(id, country);
    }

    @Override
    public int CountryProgrammerInCountry(peaksoft.config.enums.Country countryName) {
        return countryRepository.CountryProgrammerInCountry(countryName);
    }
}
