package peaksoft.services;

import peaksoft.config.enums.Country;
import peaksoft.model.Programmer;

import java.util.List;

public interface ProgrammerService {
    String saveProgrammer(Programmer programmer, Long id);
    String saveAllProgrammer(List<Programmer> programmers, Long id);
    void addConstrainToEmail();
    List<Programmer> getAllProgrammer();
    Programmer findByProgrammerId(Long id);
    String deleteProgrammerId(Long id);
    void deleteAllProgrammer();
    Programmer updateProgrammer(Long id,Programmer programmer);
   int getProgrammerWithSameCountry(Country countryName);
    Programmer findTheYoungestProgrammer();
    Programmer findTheOldestProgrammer();
}
