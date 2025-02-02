package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneSchedulataRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateComunicazioneSchedulataRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Comunicazione;
import com.example.gestionaleAzienda.domain.entities.ComunicazioneSchedulata;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.exceptions.ComunicazioneNotFoundException;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.ComunicazioneSchedulataMapper;
import com.example.gestionaleAzienda.repositories.ComunicazioneSchedulataRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ComunicazioneSchedulataService implements Job{

    @Autowired
    private ComunicazioneSchedulataRepository comunicazioneSchedulataRepository;
    @Autowired
    private ComunicazioneSchedulataMapper comunicazioneSchedulataMapper;
    @Autowired
    private ComunicazioneService comunicazioneService;
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private Scheduler scheduler;

    public ComunicazioneSchedulata getById(Long id){
        return comunicazioneSchedulataRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Comunicazione con id " + id + " non trovata"));
    }

    public List<ComunicazioneSchedulata> getAllByIdUtente(Long id){
        List<ComunicazioneSchedulata> comunicazioni = comunicazioneSchedulataRepository.findAllByIdDipendente(id);

        if (comunicazioni.isEmpty()) {
            throw new ComunicazioneNotFoundException("Nessuna comunicazione trovata per il dipendente con ID: " + id);
        }

        return comunicazioni;
    }

    public List<ComunicazioneSchedulata> getAll(){
        return comunicazioneSchedulataRepository.findAll();
    }

    public EntityIdResponse creaComunicazioneSchedulata(ComunicazioneSchedulataRequest request) throws MyEntityNotFoundException, SchedulerException {
        // Verifico che l'utente esista e lo prendo
        Dipendente dip = dipendenteService.getById(request.utente_id().id());

        ComunicazioneSchedulata comunicazioneSchedulata = comunicazioneSchedulataRepository.save(ComunicazioneSchedulata
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .publishTime(request.publishTime())
                .dipendente(dipendenteService.getById(request.utente_id().id()))
                .build());

        comunicazioneSchedulataRepository.save(comunicazioneSchedulata);

        ComunicazioneRequest comunicazioneRequest = ComunicazioneRequest
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .utente_id(request.utente_id())
                .build();
        JobDetail jobDetail = buildJobDetail(comunicazioneSchedulata, comunicazioneRequest);
        org.quartz.Trigger trigger = buildJobTrigger(jobDetail, Date.from(comunicazioneSchedulata.getPublishTime().atZone(ZoneId.systemDefault()).toInstant()));
        scheduler.scheduleJob(jobDetail, trigger);
        return EntityIdResponse.builder().id(comunicazioneSchedulata.getId()).build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Date publishTime) {

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .startAt(publishTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

    }

    private JobDetail buildJobDetail(ComunicazioneSchedulata comunicazioneSchedulata,
                                     ComunicazioneRequest comunicazioneRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("entityData", comunicazioneRequest); // ---> l'entità che passerò all'execute
        jobDataMap.put("id", comunicazioneSchedulata.getId()); // ---> l'id del job
        return JobBuilder
                .newJob(ComunicazioneSchedulataService.class)
                .withIdentity(String.valueOf(comunicazioneSchedulata.getId()), "comunicazioni")
                .storeDurably()
                .setJobData(jobDataMap)
                .build();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        ComunicazioneRequest request = (ComunicazioneRequest) jobDataMap.get("entityData");
        Long id_scheduled = jobDataMap.getLongValue("id");
        try {
            comunicazioneService.creaComunicazione(request);
        } catch (MyEntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        comunicazioneSchedulataRepository.deleteById(id_scheduled);
    }

    public EntityIdResponse updateComunicazioneSchedulata(Long id, UpdateComunicazioneSchedulataRequest request) throws SchedulerException {
        ComunicazioneSchedulata comunicazioneSchedulata = comunicazioneSchedulataRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("La comunicazione schedulata con " + id + " non è presente"));
        JobKey jobKey = new JobKey(String.valueOf(comunicazioneSchedulata.getId()), "comunicazioni");
        scheduler.deleteJob(jobKey);
        ComunicazioneSchedulataRequest comunicazioneSchedulataRequest = ComunicazioneSchedulataRequest
                .builder()
                .titolo(request.titolo() == null ? comunicazioneSchedulata.getTitolo() : request.titolo())
                .contenuto(request.contenuto() == null ? comunicazioneSchedulata.getContenuto() : request.contenuto())
                .immagine(request.immagine() == null ? comunicazioneSchedulata.getImmagine() : request.immagine())
                .publishTime(request.publishTime() == null ? comunicazioneSchedulata.getPublishTime() : request.publishTime())
                .build();
        comunicazioneSchedulataRepository.deleteById(id);
        return creaComunicazioneSchedulata(comunicazioneSchedulataRequest);
    }

    public void deleteComunicazioneSchedulataById(Long id) throws SchedulerException {
        ComunicazioneSchedulata comunicazioneSchedulata = comunicazioneSchedulataRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("La comunicazione schedulata con " + id + " non è presente"));
        JobKey jobKey = new JobKey(String.valueOf(comunicazioneSchedulata.getId()), "comunicazioni");
        scheduler.deleteJob(jobKey);
        comunicazioneSchedulataRepository.deleteById(id);
    }


}
