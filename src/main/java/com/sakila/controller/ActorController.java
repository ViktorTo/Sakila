package com.sakila.controller;

import com.sakila.entity.Actor;
import com.sakila.entity.Film;
import com.sakila.logic.Manager;
import com.sakila.utility.SceneChanger;
import com.sakila.utility.SceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ActorController {

    private final Manager manager = new Manager();
    private final SceneChanger sceneChanger = new SceneChanger();
    private Actor actor;
    private SceneView view;


    @FXML
    private Button cancelBtn;

    @FXML
    private Button doneBtn;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;


    @FXML
    void cancelActor(MouseEvent event) throws IOException {
        sceneChanger.mainScene(event);

    }

    @FXML
    void updateActorDone(MouseEvent event) throws IOException {
        
        actor.setFirstName(firstNameTxt.getText());
        actor.setLastName(lastNameTxt.getText());
        manager.updateActor(actor);
        sceneChanger.mainScene(event);

    }
    @FXML
    void createActorDone(MouseEvent event) throws IOException{
    Actor actor = new Actor();
    actor.setFirstName(firstNameTxt.getText());
    actor.setLastName(lastNameTxt.getText());
    actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
    manager.createActor(actor);
    sceneChanger.mainScene(event);

   // if (view.equals(SceneView.CREATEACTOR)) {
    //    sceneChanger.mainScene(event);
   // } else {
   //     sceneChanger.changeSceneLogin(event);
   // }
    }

    public void initData(SceneView view, Actor actor) {

        switch (view) {
            case CREATEACTOR ->{

            }

            case UPDATEACTOR -> {
                this.actor = actor;
                firstNameTxt.setText(actor.getFirstName());
                lastNameTxt.setText(actor.getLastName());

            }
        }


        }

    }

