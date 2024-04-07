package com.host;

import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name = "host", schema = "ezban")
public class HostVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "host_no", nullable = false)
	private Integer hostNo;

	@Column(name = "host_account", length = 20)
	private String hostAccount;

	@Column(name = "host_pwd", length = 20)
	private String hostPwd;

	@Column(name = "host_name", length = 50)
	private String hostName;

	@Column(name = "host_mail", length = 50)
	private String hostMail;

	@Column(name = "host_phone", length = 15)
	private String hostPhone;

	@Column(name = "host_status")
	private Byte hostStatus;

	public HostVo() {

	}

	public HostVo(Integer hostNo, String hostAccount, String hostPwd, String hostName, String hostMail,
			String hostPhone, Byte hostStatus) {
		this.hostNo = hostNo;
		this.hostAccount = hostAccount;
		this.hostPwd = hostPwd;
		this.hostName = hostName;
		this.hostMail = hostMail;
		this.hostPhone = hostPhone;
		this.hostStatus = hostStatus;
	}

	public Integer getHostNo() {
		return hostNo;
	}

	public void setHostNo(Integer hostNo) {
		this.hostNo = hostNo;
	}

	public String getHostAccount() {
		return hostAccount;
	}

	public void setHostAccount(String hostAccount) {
		this.hostAccount = hostAccount;
	}

	public String getHostPwd() {
		return hostPwd;
	}

	public void setHostPwd(String hostPwd) {
		this.hostPwd = hostPwd;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostMail() {
		return hostMail;
	}

	public void setHostMail(String hostMail) {
		this.hostMail = hostMail;
	}

	public String getHostPhone() {
		return hostPhone;
	}

	public void setHostPhone(String hostPhone) {
		this.hostPhone = hostPhone;
	}

	public Byte getHostStatus() {
		return hostStatus;
	}

	public void setHostStatus(Byte hostStatus) {
		this.hostStatus = hostStatus;
	}
	
	@Override
    public String toString() {
        return "Host{" +
                "hostNo=" + hostNo +
                ", hostAccount='" + hostAccount + '\'' +
                ", hostPwd='" + hostPwd + '\'' +
                ", hostMail='" + hostMail + '\'' +
                ", hostPhone='" + hostPhone + '\'' +
                ", hostStatus=" + hostStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HostVo host = (HostVo) o;
        return Objects.equals(hostNo, host.hostNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostNo);
    }
}

