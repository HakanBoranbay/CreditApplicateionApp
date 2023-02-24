package com.hakanboranbay.creditapp.jdbc;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;

public interface IJdbcRepository {
	
	int save(Client client);
    int update(Client client);
    Client findById(String idNo);
    int deleteById(String idNo);
    int saveCreditApplication(CreditApplication creditApplication);

}
