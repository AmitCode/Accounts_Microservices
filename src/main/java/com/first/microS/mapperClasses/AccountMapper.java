package com.first.microS.mapperClasses;

import com.first.microS.dto.AccountsDto;
import com.first.microS.entity.Accounts;

public class AccountMapper {
    /**
     * Maps an {@link Accounts} entity to an {@link AccountsDto} dto.
     *
     * @param accounts the account entity to be mapped
     * @return the corresponding account dto
     */
    public static AccountsDto mapEntityToDto(Accounts accounts){
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setCustomerId(accounts.getCustomerId());
        accountsDto.setAccount_no(accounts.getAccount_no());
        accountsDto.setAccount_type(accounts.getAccount_type());
        accountsDto.setAccount_status(accounts.getAccount_status());
        accountsDto.setBranch_name(accounts.getBranch_name());
        accountsDto.setBranch_address(accounts.getBranch_address());
        return accountsDto;
    }

    /**
     * Maps a given {@link AccountsDto} to a {@link Accounts}.
     *
     * @param accountsDto the given {@link AccountsDto} to be mapped.
     * @return the mapped {@link Accounts}.
     */
    public static Accounts mapDtoToEntity(AccountsDto accountsDto){
        Accounts accounts = new Accounts();
        accounts.setCustomerId(accountsDto.getCustomerId());
        accounts.setAccount_no(accountsDto.getAccount_no());
        accounts.setAccount_type(accountsDto.getAccount_type());
        accounts.setAccount_status(accountsDto.getAccount_status());
        accounts.setBranch_name(accountsDto.getBranch_name());
        accounts.setBranch_address(accountsDto.getBranch_address());
        return accounts;
    }
}
