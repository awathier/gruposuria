package br.com.gruposuria.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.gruposuria.entity.Aluno;
import br.com.gruposuria.entity.Turma;
import br.com.gruposuria.model.TurmaModel;

@ManagedBean
@SessionScoped
public class AgendaMB {
	
	@Inject
	private TurmaModel turmaModel;
	
	private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    private List<Turma> turmas = new ArrayList<Turma>();
 
    @PostConstruct
    public void init() {
    	this.turmas = turmaModel.listaTurmas();
        eventModel = new DefaultScheduleModel();

    	for (Turma turma : turmas) {
    		eventModel.addEvent(new DefaultScheduleEvent(turma.getCurso().getNome(), turma.getData(), turma.getDataFim(), turma.getCodigo()));
		}
    }
              
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
    
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
 }
