import React, { useState, useEffect } from "react";
import Config from '../config.json';
import Adapter, { post, get, put } from '../adapters/OrdinaryAdapter';
import Navbar from '../components/Navbar.js';
import Lists from '../components/Lists.js';

async function GetAllListWithTasks() {
    try {
        const response = await get(Config.apiURL + 'list/');
        const lists = response.data || [];
        
        // Para cada lista, busca todas as tarefas associadas
        const listsWithTasks = await Promise.all(
            lists.map(async (list) => {
                const tasksResponse = await get(Config.apiURL + `list/${list.id}`);
                const tasks = tasksResponse.data.tasks || [];
                // var subtask = [];
                // try{
                //     subtask.push(tasks.forEach(task => get(Config.apiURL+'subtask/'+task.id)));
                // }
                // catch(e){}
                // console.log(subtask);
                return { ...list, tasks };
            })
        );
        
        return listsWithTasks;
    } catch (error) {
        console.error("Erro ao carregar Listas com Tarefas", error);
        return []; // Retorna uma lista vazia em caso   de erro
    }
}

async function InsertTitleList(data) {
    try {
        const response = await post(Config.apiURL + 'list/', data);
        return response.data;
    } catch (error) {
        console.error("Erro ao inserir nome da lista.", error);
        throw error;
    }
}

async function UpdateTitleList(data) {
    try {
        const response = await put(Config.apiURL + 'list/' + data);
        return response.data;
    } catch (error) {
        console.error("Erro ao atualizar nome da lista.", error);
        throw error;
    }
}

const formatDateToScreen = (dateStr) => {
    const [day, month, year] = dateStr.split('/');
    return `${year}-${month}-${day}`;
};

export default function TaskList() {
    //add dados as listas (adicionar status -> userState)
    const [list, setLists] = useState([]);
    const [selectedUserIds, setSelectedUserIds] = useState([]);
        
    useEffect(() => {
        async function fetchBoards() {
            try {
                const data = await GetAllListWithTasks();
                console.log("LISTAS Home.js: ", data);
                data.forEach(list =>{
                    list.tasks.forEach(task => {
                        console.log(task);
                        task.endDate = formatDateToScreen(task.endDate);
                        task.startDate = formatDateToScreen(task.startDate);
                    });
                })

                setLists(data); 
            } catch (error) {
                console.error("Erro ao carregar Listas.");
            }
        }
        fetchBoards();
    }, []);

    const addNewList = async () => {
        const requestBody = {
            "title": "Nova Lista"
        }

        try {
            const savedList = await InsertTitleList(requestBody);
            setLists([...list, savedList]);
        } catch (error) {
            console.error("Erro ao inserir nome da lista.");
        }
    };

    const updateListTitle = (index, newTitle) => {
        setLists(prevLists => {
            if (!Array.isArray(prevLists)) {
                console.error('prevLists is not an array:', prevLists);
                return prevLists; // Retorna o estado anterior sem modificar
            }
    
            const updatedLists = [...prevLists];
            if (!updatedLists[index]) {
                console.error('No list found at index:', index);
                return prevLists; // Retorna o estado anterior sem modificar
            }
    
            updatedLists[index].title = newTitle;
            return updatedLists;
        });
    
        const idList = list[index]?.id;
        if (!idList) {
            console.error('List ID not found for index:', index);
            return;
        }
    
        const uri = idList + "/title/" + newTitle;
        try {
            UpdateTitleList(uri);
        } catch (error) {
            console.error("Erro ao atualizar nome da lista.", error);
        }
    };

    const handleUserSelection = (ids) => {
        setSelectedUserIds(ids);
    };

    return (
        <div>        
            <Navbar addNewBoard={addNewList} />
            <div className="row">
                <Lists lists={list} updateListTitle={updateListTitle} onUserSelect={handleUserSelection}/>
            </div>
        </div>
    );
}