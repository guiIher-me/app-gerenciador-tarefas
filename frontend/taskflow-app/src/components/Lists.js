import React, { useState } from "react";         
import { TextField, Card, Button } from '@mui/material/';
import Adapter, { post, del } from '../adapters/OrdinaryAdapter';
import { createSvgIcon } from '@mui/material/utils';
import Config from '../config.json';
import { styled } from '@mui/system';
import Tasks from './Tasks'; // Importando o componente Tasks
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
    margin:'15px',
});

export default function ReturnLists({ lists, updateListTitle }) { 

    const [tempTitles, setTempTitles] = useState({}); 
    const [progressoCards, setProgressoCards] = useState({}); // Estado para os cards de progresso

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
        setProgressoCards(prev => ({
            ...prev,
            [listId]: [...(prev[listId] || []), { title: "", description: "", usersIds: [], listId, startDate: "", endDate: "", isSaved: false }]
        }));
    };

    const handleProgressoChange = (listId, cardIndex, field, value) => {
        setProgressoCards(prev => {
            const updatedCards = [...prev[listId]];
            updatedCards[cardIndex][field] = value;

            // Verifica se todos os campos obrigatórios estão preenchidos
            const { title, description, startDate, endDate } = updatedCards[cardIndex];
            if (title && description && startDate && endDate && !updatedCards[cardIndex].isSaved) {
                // Faz o POST via API se todos os campos estiverem preenchidos e não estiver salvo
                saveToApi(listId, cardIndex, updatedCards[cardIndex]);
            }

            return { ...prev, [listId]: updatedCards };
        });
    };

    //Formata as daas para a api: dd/mm/aaaa
    const formatDate = (dateStr) => {
        const date = new Date(dateStr);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    };

    const saveToApi = async (listId, cardIndex, cardData) => {
        try {
            //Formata as datas para ser compatível com a api
            const formattedStartDate = formatDate(cardData.startDate);
            const formattedEndDate = formatDate(cardData.endDate);

            const requestBody = {
                "title": cardData.title,
                "description": cardData.description,
                "usersIds": /*VETOR COM OS ID DOS USUÁRIOS SELECIONADOS*/[52,152],
                "listId": listId,
                "startDate": formattedStartDate,
                "endDate": formattedEndDate
            }
            console.log(requestBody);
            const response = await post(Config.apiURL+'task/', requestBody);
            console.log('DEU CERTO: ', response);

            // Atualiza o estado para marcar a task como salva e salvar o taskId retornado
            setProgressoCards(prev => {
                const updatedCards = [...prev[listId]];
                updatedCards[cardIndex].isSaved = true;
                //updatedCards[cardIndex].taskId = response.data.id; // Supondo que o ID da task está no campo `id` do response
                return { ...prev, [listId]: updatedCards };
            });

        } catch (error) {
            console.error('Erro ao salvar:', error);
        }
    };

    const handleDeleteTask = async (listId, cardIndex) => {
        if (!progressoCards[listId] || !progressoCards[listId][cardIndex]) {
            console.error('ProgressoCards or ProgressoCards[listId][cardIndex] is undefined');
            return;
        }
    
        const taskId = progressoCards[listId][cardIndex]?.taskId;
        try {
            if(taskId){
                const response = await del(Config.apiURL+'task/'+taskId);
                console.log('DELETAR: '+response);
            }
    
            setProgressoCards(prev => {
                const updatedCards = [...prev[listId]];
                updatedCards.splice(cardIndex, 1); // Remove o card deletado do estado
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
                            tasks={list.tasks} // Passa as tarefas para o componente Tasks
                            progressoCards={progressoCards}
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
