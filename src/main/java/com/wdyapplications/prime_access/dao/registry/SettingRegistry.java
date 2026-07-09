package com.wdyapplications.prime_access.dao.registry;

import com.wdyapplications.prime_access.dao.entity.Setting;
import com.wdyapplications.prime_access.dao.repository.SettingRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SettingRegistry {
    private final SettingRepository repository;
    private final Map<String, Object> settingVersionApp = new ConcurrentHashMap<>();

    public SettingRegistry(SettingRepository repository) {
        this.repository = repository;
        loadSettings();
    }

    public void loadSettings() {
        List<Setting> allSettings = repository.findAll();
        settingVersionApp.clear();
        allSettings.forEach(channel -> settingVersionApp.put(channel.getCode(), channel.getValeur()));
    }

    public Object findByCode(String name) {
         Object isi =  settingVersionApp.get(name);
        return isi;
    }

    public Map<String, Object> getInfoApp() {
        return settingVersionApp;
    }

    public void refresh() {
        loadSettings(); // reloads from DB
    }

}
