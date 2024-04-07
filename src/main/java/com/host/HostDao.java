package com.host;

import java.util.List;

public interface HostDao {
	
	int insert(HostVo host); 	
	int update(HostVo host);
	int delete(Integer hostNo);
	HostVo findByPK(Integer hostNo);
	List<HostVo> getAll();

}
