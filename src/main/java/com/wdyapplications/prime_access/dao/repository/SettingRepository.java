

package com.wdyapplications.prime_access.dao.repository;

import com.wdyapplications.prime_access.dao.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wdyapplications.prime_access.dao.repository.base._SettingRepository;

/**
 * Repository : Setting.
 *
 * @author dorgeddy
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer>, _SettingRepository {

}
