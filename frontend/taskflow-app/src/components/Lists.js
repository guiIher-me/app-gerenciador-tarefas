import React, { useState, useEffect } from "react";
import { TextField, Card, Button } from '@mui/material/';
import Adapter, { post, del, get, put } from '../adapters/OrdinaryAdapter';
import { createSvgIcon } from '@mui/material/utils';
import Config from '../config.json';
import { styled } from '@mui/system';
import Tasks from './Tasks';
import axios from 'axios';

const HomeIcon = createSvgIcon(
    <path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z" />,
    'Home',
);

const PlusIcon = createSvgIcon(
    <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        strokeWidth={1.5}
        stroke="currentColor"
    >
        <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
    </svg>,
    'Plus',
);

const CardPendente = styled(Card)({
    backgroundColor: "#002364",
    borderRadius: '20px',
    padding: '16px',
    marginBottom: '16px'
});

const ButtonCriarTarefa = styled(Button)({
    margin: '15px',
});

export default function Lists({ lists, updateListTitle }) {

    const [tempTitles, setTempTitles] = useState({});
    const [progressoCards, setProgressoCards] = useState({});

    useEffect(() => {
        const initializeProgressoCards = () => {
            const initialProgressoCards = {};
            console.log("lists.tasks: ", lists);
                lists.forEach(list => {
                    if(list.tasks){
                        const tasksWithSavedFlag = list.tasks.map(task => ({ ...task, isSaved: true }));
                        initialProgressoCards[list.id] = tasksWithSavedFlag;
                    }
                });
            

            setProgressoCards(initialProgressoCards);
        };
        initializeProgressoCards();
    }, [lists]);

    const handleChange = (index, newTitle) => {
        setTempTitles(prev => ({ ...prev, [index]: newTitle }));
    };

    const handleBlur = (index) => {
        if (tempTitles[index] !== undefined && tempTitles[index] !== lists[index].title) {
            updateListTitle(index, tempTitles[index]);
        }
    };

    const handleKeyDown = (index, e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            handleBlur(index);
        }
    };

    const handleAddCardEmProgresso = (listId) => {
        setProgressoCards(prev => {
            const updatedList = prev[listId] ? [...prev[listId]] : [];
            updatedList.push({ title: "", description: "", usersIds: [], listId, startDate: "", endDate: "", isSaved: false });
            return { ...prev, [listId]: updatedList };
        });
    };

    const handleProgressoChange = (listId, cardIndex, field, value) => {
        setProgressoCards(prev => {
            if (!prev[listId]) {
                console.error(`A lista com ID ${listId} não existe no estado.`);
                return prev;
            }

            const updatedCards = [...prev[listId]];
            updatedCards[cardIndex][field] = value;

            const { title, description, startDate, endDate } = updatedCards[cardIndex];
            if (title && description && startDate && endDate && !updatedCards[cardIndex].isSaved) {
                saveToApi(listId, cardIndex, updatedCards[cardIndex]);
            }

            return { ...prev, [listId]: updatedCards };
        });
    };

    const formatDateToBR = (dateStr) => {
        const date = new Date(dateStr);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    };

    const getUsersByIds = async (users) => {
        try {
            const response = await Promise.all(users.map(user => get(Config.apiURL + `user/${user.id}`)));
            return response.map(res => res.data);
        } catch (error) {
            console.error('Erro ao buscar usuários por IDs:', error);
            return [];
        }
    };
    

    const saveTaskToApi = async (taskData) => {
        
        try {            
            const response = await post(Config.apiURL + 'task/', taskData);
            return response.data;
        } catch (error) {
            console.error('Erro ao salvar tarefa na API:', error);
            return null;
        }
    };

    const saveToApi = async (listId, cardIndex, cardData) => {
        try {
            cardData.startDate = formatDateToBR(cardData.startDate);
            cardData.endDate = formatDateToBR(cardData.endDate);
            console.log("cardData: ", cardData);
            const newTask = await saveTaskToApi(cardData);
            console.log("newTask: ", newTask);
            if (newTask) {
                // Obtenha os nomes dos usuários selecionados
                const updatedUsers = await getUsersByIds(newTask.users);
                
                // Atualize o estado de progressoCards com os nomes dos usuários
                setProgressoCards(prev => {
                    const updatedCards = prev[listId] ? [...prev[listId]] : [];
                    updatedCards[cardIndex] = {
                        ...newTask,
                        isSaved: true,
                        taskId: newTask.id,
                        startDate: formatDateToScreen(newTask.startDate),
                        endDate: formatDateToScreen(newTask.endDate),
                        usersNames: updatedUsers.map(user => user.name) // Adicione os nomes dos usuários selecionados
                    };
                    return { ...prev, [listId]: updatedCards };
                });
            }
        } catch (error) {
            console.error('Erro ao salvar/atualizar tarefa:', error);
        }
    };
    
    
    

    const formatDateToScreen = (dateStr) => {
        const [day, month, year] = dateStr.split('/');
        return `${year}-${month}-${day}`;
    };

    const handleDeleteTask = async (listId, cardIndex, taskId) => {
        if (!progressoCards[listId] || !progressoCards[listId][cardIndex]) {
            console.error('ProgressoCards or ProgressoCards[listId][cardIndex] is undefined');
            return;
        }

        console.log("taskId: ", taskId);
        try {
            if (taskId) {
                const response = await del(Config.apiURL + 'task/' + taskId);
                console.log('DELETAR: ' + response);
            }

            setProgressoCards(prev => {
                const updatedCards = [...prev[listId]];
                updatedCards.splice(cardIndex, 1);
                return { ...prev, [listId]: updatedCards };
            });
        } catch (error) {
            console.error('Erro ao deletar:', error);
        }
    };

    return (
        <div>
            <div className="row">
                {lists.map((list, index) => (
                    <CardPendente key={index} id={list.id} className="col-md-3 mx-auto my-1">
                        <TextField
                            variant="standard"
                            fullWidth
                            value={tempTitles[index] !== undefined ? tempTitles[index] : list.title}
                            onChange={(e) => handleChange(index, e.target.value)}
                            onBlur={() => handleBlur(index)}
                            onKeyDown={(e) => handleKeyDown(index, e)}
                            InputProps={{
                                style: { color: '#fff' }
                            }}
                        />
                        <Tasks
                            tasks={progressoCards[list.id] || []}
                            handleProgressoChange={handleProgressoChange}
                            handleDeleteTask={handleDeleteTask}
                            listId={list.id}
                        />
                        <ButtonCriarTarefa variant="text" startIcon={<PlusIcon />} onClick={() => handleAddCardEmProgresso(list.id)}>Nova Tarefa</ButtonCriarTarefa>
                    </CardPendente>
                ))}
            </div>
        </div>
    );
}
