package com.bob.azure.service.impl;


import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.bob.azure.entity.Group;
import com.bob.azure.repository.GroupRepository;
import com.bob.azure.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public List<Group> getGroups() {
        return IterableUtils.toList(groupRepository.findAll());
    }
}
