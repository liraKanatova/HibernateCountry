package peaksoft;

import peaksoft.config.HibernateConfig;
import peaksoft.config.enums.Status;
import peaksoft.model.Address;
import peaksoft.model.Country;
import peaksoft.model.Programmer;
import peaksoft.model.Project;
import peaksoft.repositories.AddressRepository;
import peaksoft.repositories.AddressRepositoryImpl;
import peaksoft.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static  void main(String[] args )
    {
      //  HibernateConfig.createEntityManagerFactory();





        System.out.println(HibernateConfig.createEntityManagerFactory());
        CountryServices countryServices = new CountryServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();
        ProgrammerService programmerService = new ProgrammerServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        while (true){
            System.out.println("""
            ~~~~~~~~Country~~~~~~~~
            1.SAVE COUNTRY
            2.SAVE ALL
            3.GET ALL
            4.FIND BY ID
            5.DELETE BY ID
            6.DELETE ALL
            7.UPDATE
            8.LONG DESCRIPTION
            9.COUNT PROGRAMMER IN COUNTRY
            
            ~~~~~~~~~ADDRESS~~~~~~~~~~~~
            10.SAVE
            11.SAVE ALL
            12.GET ALL
            13.FIND BY ID
            14.DELETE BY ID
            15.DELETE ALL
            16.UPDATE
            
            ~~~~~~~~~PROGRAMMER~~~~~~~~~~~
            17.SAVE
            18.SAVE ALL
            19.ADD CONSTRAINT
            20.DELETE ALL
            21.UPDATE
            22.FIND BY COUNTRY
            23.FIND YOUNGEST
            24.FIND ELDEST
            
            ~~~~~~~~PROJECT~~~~~~~~~~~~~~
            25.SAVE
            26.SAVE ALL
            27.GET ALL
            28.FIND BY ID
            29.DELETE BY ID
            30.DELETE ALL
            31.UPDATE
            32.ASSIGN PROGRAMMER TO PROJECT
            33.EXPENSIVE PROJECT
            34.FAST WRITTEN PROJECT
           
            
             write command:""");
            Scanner scanner=new Scanner(System.in);
            int number= scanner.nextInt();
            switch (number) {
                case 1:
                    System.out.println(countryServices.saveCountry(new Country(peaksoft.config.enums.Country.KYRGYZSTAN,
                            "Kyrgyzstan is a beautiful country")));
                    break;
                case 10:
                    System.out.println(addressService.saveAddress(new Address
                            ("Bishkek", "Chui", 92), 1L));
                    break;
                case 17:
                    System.out.println(programmerService.saveProgrammer(new Programmer("Kanatova Lira", "l@gmail.com", LocalDate.of(1984, 7, 7), Status.COLLABORATOR), 1L));

                    break;
                case 25:
                    System.out.println(projectService.saveProject(new Project("Googleclass", "Site", LocalDate.of(2022, 12, 29), LocalDate.of(2023, 1, 30), 100000)));

                    break;
                case 11:
                    List<Address> addressList = new ArrayList<>(List.of(new Address("Chicago", "Chicago", 120),
                            new Address("Moskva", "Makadana", 45),
                            new Address("Kyzyl- Ai", "Menecshe", 3),
                            new Address("Talas", "Chynyke Biy", 100)));
                    addressService.saveAllAddress(addressList, 2L);
                    break;
                case 2:
                    List<Country> countryList = new ArrayList<>(List.of(new Country(peaksoft.config.enums.Country.USA, "There are fifty states in USA"),
                            new Country(peaksoft.config.enums.Country.RUSSIA, "In terms of land area," +
                                    " Russia is the largest country in the world"),
                            new Country(peaksoft.config.enums.Country.TURKEY, "Turkey is not only one of the most popular resorts in the world"),
                            new Country(peaksoft.config.enums.Country.KYRGYZSTAN, "Kyrgyzstan is a beautiful country")));
                    countryServices.saveAllCountry(countryList);
                    break;
                case 18:
                    List<Programmer> programmerList = new ArrayList<>(List.of(new Programmer("Ashyrbaeva Eliza", "eliza@gmail.com",
                                    LocalDate.of(2004, 2, 2), Status.CONTRIBUTOR),
                            new Programmer("Muratova Nuriza", "nuriza@gmail.com",
                                    LocalDate.of(2005, 10, 1), Status.COLLABORATOR),
                            new Programmer("Asangazieva Aijamal", "aijamal@gmail.com",
                                    LocalDate.of(1997, 7, 13), Status.OWNER),
                            new Programmer("Toichubai uulu Muhammed", "muhammed@gmail.com",
                                    LocalDate.of(1999, 1, 20), Status.OWNER)));
                    programmerService.saveAllProgrammer(programmerList, 2L);
                    break;
                case 26:
                    List<Project> projectList = new ArrayList<>(List.of(new Project("ChatGPT", "Language model", LocalDate.of(2022, 4, 12), LocalDate.of(2023, 2, 1), 90800),
                            new Project("TalentLms", "website", LocalDate.of(2022, 9, 10), LocalDate.of(2023, 2, 6), 50000)));
                    projectService.saveAllProject(projectList);
                    break;
                case 19:
                    programmerService.addConstrainToEmail();
                    break;
                case 3:
                    System.out.println(countryServices.getAllCountry());
                    break;
                case 4:
                    System.out.println(countryServices.findByCountryId(1L));
                    break;
                case 5:
                    System.out.println(countryServices.deleteCountryById(2L));
                    break;
                case 6:
                    countryServices.deleteAllCounty();
                    break;
                case 7:
                    System.out.println(countryServices.updateCountry(3L, new Country(peaksoft.config.enums.Country.TURKEY, "Turkey is beautiful")));
                    break;
                case 8:
                    System.out.println(countryServices.findTheLongestDescription());
                    break;
                case 9:
                    System.out.println(countryServices.CountryProgrammerInCountry(peaksoft.config.enums.Country.TURKEY));
                    break;
                case 12:
                    System.out.println(addressService.getAllAddress());
                    break;
                case 13:
                    System.out.println(addressService.findByAddressId(2L));
                    break;
                case 14:
                    System.out.println(addressService.deleteAddressId(1L));
                    break;
                case 15:
                    addressService.deleteAllAddress();
                    break;
                case 16:
                    System.out.println(addressService.updateAddress(4L, new Address("Osh", "Kara Suu", 76)));
                    break;
                case 20:
                    programmerService.deleteAllProgrammer();
                    break;
                case 21:
                    System.out.println(programmerService.updateProgrammer(2L, new Programmer("Bektenova Aigerim", "a@gmail.com",
                            LocalDate.of(2005, 9, 2), Status.CONTRIBUTOR)));
                    break;
                case 22:
                    System.out.println(programmerService.getProgrammerWithSameCountry(peaksoft.config.enums.Country.RUSSIA));
                    break;
                case 23:
                    System.out.println(programmerService.findTheYoungestProgrammer());
                    break;
                case 24:
                    System.out.println(programmerService.findTheOldestProgrammer());
                    break;
                case 27:
                    System.out.println(projectService.getAllProject());
                    break;
                case 28:
                    System.out.println(projectService.findByProjectId(2L));
                    break;
                case 29:
                    System.out.println(projectService.deleteProjectId(1L));
                    break;
                case 30:
                    System.out.println(projectService.getAllProject());
                    break;
                case 31:
                    System.out.println(projectService.updateProject(3L, new Project("WhatsApp", "website", LocalDate.of(2022, 4, 12),
                            LocalDate.of(2023, 2, 1), 90800)));
                case 32:
                    projectService.assignProgrammerToProject(3L, 3L);
                    break;
                case 33:
                    System.out.println(projectService.findTheBestExpensiveProject());
                    break;
                case 34:
                    System.out.println(projectService.findTheFastWrittenProject());
                    break;

            }
        }
    }
}


