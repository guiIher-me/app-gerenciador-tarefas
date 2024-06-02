import React, { useState, useEffect } from "react";
import Config from '../config.json';
import Adapter, { post, get, put } from '../adapters/OrdinaryAdapter';
// import { useNavigate } from "react-router-dom";
// import { styled } from '@mui/system';
// import {Card, Typography} from '@mui/material/';
import Navbar from '../components/Navbar.js';
import Lists from '../components/Lists.js';


// const CardConcluidos = styled(Card)({
//     backgroundColor: "#00bf63",
//     borderRadius: '20px'
// });

// const TypographyTitulo = styled(Typography)({
//     textAlign: 'center',
//     fontWeight: 'bold',
//     marginBottom: '10px'
// });

// const TypographyDescricao= styled(Typography)({
//     textAlign: 'left',
//     color: '#ffffff'
// });

async function GetAllList() {
    try {
        const response = await get(Config.apiURL + 'list/')
        return response.data;
    } catch (error) {
        console.error("Erro ao carregar Listas", error);
        throw error;
    }
}

async function InsertTitleList(data){

    try{
        const response = await post(Config.apiURL + 'list/', data)
        return response.data;

    } catch(error){
        console.error("Erro ao inserir nome da lista.", error);
        throw error;
    }
}

async function UpdateTitleList(data){

    try{
        const response = await put(Config.apiURL + 'list/' + data);
        return response.data;
    } catch(error){
        console.error("Erro ao atualizar nome da lista.", error);
        throw error;
    }
}

export default function TaskList(){

    const [list, setLists] = useState([]);
        
    useEffect(() => {
        async function fetchBoards() {
            try {
                const data = await GetAllList();
                setLists(data); 
            } catch (error) {
                console.error("Erro ao carregar Listas.");
            }
        }
        fetchBoards();
    }, []);

    const addNewList = async () => {
        
        // Define "Nova Lista" como padrão para o usuário editar posteriormente
        const requestBody = {
            "title": "Nova Lista"
        }

        try {
            const savedList = await InsertTitleList(requestBody);
            console.log(savedList);
            setLists([...list, savedList]);
        } catch (error) {
            console.error("Erro ao inserir nome da lista.");
        }
    };

    const updateListTitle = (index, newTitle) => {

        setLists(prevLists => {
            const updatedLists = [...prevLists];
            updatedLists[index].title = newTitle;
            //console.log(updatedLists);
            return updatedLists;
        });

        const idList = list[index].id;
        const uri = idList + "/title/" + newTitle;

        try {
            UpdateTitleList(uri);
        } catch (error) {
            console.error("Erro ao atualizar nome da lista.");
        }
    };

    return (
        <div>        
            <Navbar addNewBoard={addNewList} />
            <div className="row">
                <Lists lists={list} updateListTitle={updateListTitle}/>
            </div>

        </div>
    )
}



