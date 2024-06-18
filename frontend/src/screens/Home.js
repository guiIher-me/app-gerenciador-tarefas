import React, { useState, useEffect } from "react";
import Config from '../config.json';
import Header from '../components/Header.js';
import List from '../components/List.js';
import ListAdder from '../components/ListAdder.js';
import { v4 as uuidv4 } from 'uuid';

import { HttpRequestAuth } from "../http";
const httpAuth = new HttpRequestAuth();

async function getDetailedLists() {
    try {
        const response = await httpAuth.get(`${Config.BASEPATH}/list/detailed`);
        const lists = response.data;
        return lists;
    } catch (error) {
        console.error("Erro ao carregar listas", error);
    }
}

export default function Home() {
    const [lists, setLists] = useState([]);
        
    useEffect(() => {
        async function fetchBoards() {
            try {
                const detailedLists = await getDetailedLists();
                setLists(detailedLists); 
            } catch (error) {
                console.error("Erro ao carregar Listas.");
            }
        }
        fetchBoards();
    }, []);

    const addList = async (previousListId) => {
        const requestBody = {
            title: "New List",
            previousListId
        };

        try {
            const responseCreate = await httpAuth.post(`${Config.BASEPATH}/list/`, requestBody);
            const newList = responseCreate.data;
            newList.tasks = [];

            insertNewList(newList);
        } catch (error) {
            console.log(error);
        }
    };

    const insertNewList = async (newList) => {
        if (!newList) return;
    
        const targetPosition = lists.findIndex(
          (list) => list.position > newList.position
        );
    
        if (targetPosition === -1) {
          setLists([...lists, newList]);
          return;
        }
    
        const updatedLists = [...lists];
        updatedLists.splice(targetPosition, 0, newList);
    
        setLists(updatedLists);
    };

    async function syncTitleList(id, newTitle) {
        try {
            const listIndex = lists.findIndex((list) => list.id === id);

            if (listIndex === -1)
                throw new Error(`Lista com ID ${id} não encontrada para atualizar o título.`);

            const updatedLists = [...lists];
            updatedLists[listIndex].title = newTitle;
            setLists(updatedLists);

        } catch (error) {
            console.error(`Erro ao sincronizar título da lista ${id}`, error);
        }
    }

    async function updateTitleList(id, newTitle) {
        try {
            await httpAuth.put(`${Config.BASEPATH}/list/${id}/${newTitle}`);
        } catch (error) {
            console.error(`Erro ao atualizar título da lista ${id}`, error);
        }
    }

    return (
        <div>
            <Header />

            <div className="container-fluid">
                <div className="row">
                    <div className="tf-list-container d-flex flex-row">

                        <ListAdder actions={ { click: addList } } />
                        {lists.map((list) =>
                            <>
                                <List key={uuidv4()} list={ list } actions={{ onBlur: syncTitleList }} />
                                <ListAdder key={uuidv4()} previousListId={ list.id } actions={ { click: addList } } />
                            </>
                        )}

                    </div>
                </div>
            </div>
        </div>
    );
}