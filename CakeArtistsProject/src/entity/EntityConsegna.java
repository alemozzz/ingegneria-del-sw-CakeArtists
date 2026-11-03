package entity;

import java.sql.Date;
import java.sql.Time;

public class EntityConsegna {
	private int idConsegna;
	private String luogoRitiro;
	private Date data;
	private Time oraRitiro;
	private Time oraConsegnaPrevista;

	public EntityConsegna() {
		super();
	}

	public EntityConsegna(int idConsegna, String luogoRitiro, Date data, Time oraRitiro, Time oraConsegnaPrevista) {
		super();
		this.idConsegna = idConsegna;
		this.luogoRitiro = luogoRitiro;
		this.data = data;
		this.oraRitiro = oraRitiro;
		this.oraConsegnaPrevista = oraConsegnaPrevista;
	}

	public int getIdConsegna() { return idConsegna; }
	public void setIdConsegna(int idConsegna) { this.idConsegna = idConsegna; }
	public String getLuogoRitiro() { return luogoRitiro; }
	public void setLuogoRitiro(String luogoRitiro) { this.luogoRitiro = luogoRitiro; }
	public Date getData() { return data; }
	public void setData(Date data) { this.data = data; }
	public Time getOraRitiro() { return oraRitiro; }
	public void setOraRitiro(Time oraRitiro) { this.oraRitiro = oraRitiro; }
	public Time getOraConsegnaPrevista() { return oraConsegnaPrevista; }
	public void setOraConsegnaPrevista(Time oraConsegnaPrevista) { this.oraConsegnaPrevista = oraConsegnaPrevista; }
}