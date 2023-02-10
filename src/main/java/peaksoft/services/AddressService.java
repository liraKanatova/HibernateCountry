package peaksoft.services;

import peaksoft.model.Address;

import java.util.List;

public interface AddressService {
    String saveAddress(Address address,Long id);
    String saveAllAddress(List<Address> addresses,Long id);
    List<Address> getAllAddress();
    Address findByAddressId(Long id);
    String deleteAddressId(Long id);
    void deleteAllAddress();
    Address updateAddress(Long id,Address address);
}
