package peaksoft.services;

import peaksoft.model.Address;
import peaksoft.repositories.AddressRepository;
import peaksoft.repositories.AddressRepositoryImpl;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    AddressRepository addressRepository = new AddressRepositoryImpl();

    @Override
    public String saveAddress(Address address,Long id) {
        return addressRepository.saveAddress(address, id);
    }

    @Override
    public String saveAllAddress(List<Address> addresses,Long id) {
        addressRepository.saveAllAddress(addresses, id);
        return "Successfully saved!";
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }

    @Override
    public Address findByAddressId(Long id) {
        return addressRepository.findByAddressId(id);
    }

    @Override
    public String deleteAddressId(Long id) {
        addressRepository.deleteAddressId(id);
        return "successfully deleted!" ;
    }

    @Override
    public void deleteAllAddress() {
        addressRepository.deleteAllAddress();

    }

    @Override
    public Address updateAddress(Long id, Address address) {
        return addressRepository.updateAddress(id, address);
    }
}
