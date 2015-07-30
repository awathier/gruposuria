package br.com.gruposuria.managedbeans;

import java.util.ArrayList;
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

import br.com.gruposuria.entity.Curso;
import br.com.gruposuria.entity.Instituicao;
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
    private Instituicao instituicao = new Instituicao();
    private Turma turma = new Turma();
    private Curso curso = new Curso();
 
    @PostConstruct
    public void init() {
    	
    	this.turmas = turmaModel.listaTurmasVigentes();
    	this.eventModel = new DefaultScheduleModel();

    	for (Turma turma : turmas) {
    		this.eventModel.addEvent(new DefaultScheduleEvent(turma.getCurso().getNome() + " - " + turma.getCidadeCurso().getNome() + "(" + turma.getCidadeCurso().getEstado().getUf() + ")", turma.getData(), turma.getDataFim(), turma.getCodigo()));
		}
    	
    }
    
    public void carregarCursos() {
    	this.turmas = turmaModel.listaTurmasVigentes();
    	this.eventModel = new DefaultScheduleModel();
    	this.turma = new Turma();

    	for (Turma turma : turmas) {
    		this.eventModel.addEvent(new DefaultScheduleEvent(turma.getCurso().getNome() + " - " + turma.getCidadeCurso().getNome() + "(" + turma.getCidadeCurso().getEstado().getUf() + ")", turma.getData(), turma.getDataFim(), turma.getCodigo()));
		}
	}
    
    /*public List<Turma> listaTurmas(){
    	
    	setTurmas(new ArrayList<Turma>());
		this.turmas = turmaModel.listaTurmas();
		
		this.eventModel = new DefaultScheduleModel();

    	for (Turma turma : turmas) {
    		this.eventModel.addEvent(new DefaultScheduleEvent(turma.getCurso().getNome(), turma.getData(), turma.getDataFim(), turma.getCodigo()));
		}
    	
		return this.turmas;
	}*/
              
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

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

 }
