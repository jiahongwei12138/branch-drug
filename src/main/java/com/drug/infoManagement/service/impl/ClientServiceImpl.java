package com.drug.infoManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchClient;
import com.drug.infoManagement.mapper.ClientMapper;
import com.drug.infoManagement.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    private ClientMapper mapper;

    @Override
    public List<BranchClient> queryAllClient(Map<String, Object> map) {
	return mapper.queryAllClient(map);
    }

    @Override
    public int getCountClient() {
	return mapper.getCountClient();
    }

    @Override
    public void deleteCliById(String clientId) {
	mapper.deleteCliById(clientId);
    }

    @Override
    public void updateCliById(BranchClient branchClient) {
	mapper.updateCliById(branchClient);
    }

    @Override
    public void addClient(BranchClient branchClient) {
	mapper.addClient(branchClient);
    }

}
