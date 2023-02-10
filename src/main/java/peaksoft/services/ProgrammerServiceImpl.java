package peaksoft.services;

import peaksoft.config.enums.Country;
import peaksoft.model.Programmer;
import peaksoft.repositories.ProgrammerRepository;
import peaksoft.repositories.ProgrammerRepositoryImpl;

import java.util.List;

public class ProgrammerServiceImpl implements ProgrammerService {
    ProgrammerRepository programmerRepository = new ProgrammerRepositoryImpl();

    @Override
    public String saveProgrammer(Programmer programmer, Long id) {
        programmerRepository.saveProgrammer(programmer, id);
        return "successfully saved";
    }

    @Override
    public String saveAllProgrammer(List<Programmer> programmers, Long id) {
        programmerRepository.saveAllProgrammer(programmers, id);
        return "successfully saved";
    }

    @Override
    public void addConstrainToEmail() {
        programmerRepository.addConstrainToEmail();

    }

    @Override
    public List<Programmer> getAllProgrammer() {
        return programmerRepository.getAllProgrammer();
    }

    @Override
    public Programmer findByProgrammerId(Long id) {
        return programmerRepository.findByProgrammerId(id);
    }

    @Override
    public String deleteProgrammerId(Long id) {
        programmerRepository.deleteProgrammerId(id);
        return "successfully deleted";
    }

    @Override
    public void deleteAllProgrammer() {
        programmerRepository.deleteAllProgrammer();

    }

    @Override
    public Programmer updateProgrammer(Long id, Programmer programmer) {
        return programmerRepository.updateProgrammer(id, programmer);
    }

    @Override
    public int getProgrammerWithSameCountry(Country countryName) {
        return programmerRepository.getProgrammerWithSameCountry(countryName);
    }

    @Override
    public Programmer findTheYoungestProgrammer() {
        return programmerRepository.findTheYoungestProgrammer();
    }

    @Override
    public Programmer findTheOldestProgrammer() {
        return programmerRepository.findTheOldestProgrammer();
    }
}
