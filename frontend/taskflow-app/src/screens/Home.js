import React, { useState, useEffect } from "react";
import Config from '../config.json';
import Adapter, { post, get } from '../adapters/OrdinaryAdapter';
import { useNavigate } from "react-router-dom";
import {TextField, Box, Card, FormControl, InputLabel, Typography, Select, MenuItem} from '@mui/material/';
import { styled } from '@mui/system';
import Navbar from '../components/Navbar.js'
import { alignProperty } from "@mui/material/styles/cssUtils.js";

const CardPendente = styled(Card)({
    backgroundColor: "#002364",
    borderRadius: '20px'
});

const CardEmProgresso = styled(Card)({
    backgroundColor: "#ffbd59",
    borderRadius: '20px'
});

const CardConcluidos = styled(Card)({
    backgroundColor: "#00bf63",
    borderRadius: '20px'
});

const TypographyTitulo = styled(Typography)({
    textAlign: 'center',
    fontWeight: 'bold',
    marginBottom: '10px'
});

const TypographyDescricao= styled(Typography)({
    textAlign: 'left',
    color: '#ffffff'
});

async function GetAllList() {
    try {
        const response = await get(Config.apiURL + 'list/')
        return response.data;
    } catch (error) {
        console.error("Erro ao carregar Quadros.", error);
        throw error;
    }
}

export default function TaskList(){

    const [selectedBoard, setSelectedBoard] = useState('');
    const [boards, setBoards] = useState([]);
    const navigate = useNavigate();
    var idList, titleList, positionList;

    useEffect(() => {
        async function fetchBoards() {
            try {
                const data = await GetAllList();
                setBoards(data); 
            } catch (error) {
                console.error("Erro ao carregar Quadros.");
            }
        }
        fetchBoards();
    }, []);

    const handleChange = (event) => {
        setSelectedBoard(event.target.value);
    };
    

    return (
        <div>            
            <Navbar/>
            <div className="row">
                <Box display="flex" justifyContent="center" alignItems="center" className="md-6" style={{ margin: '15px 0' }}>
                    <FormControl variant="outlined" size="small" className="md-6" style={{ minWidth: 240 }}>
                        <InputLabel id="select-label">Quadros</InputLabel>
                        <Select
                            labelId="select-label"
                            id="select-board"
                            value={selectedBoard}
                            onChange={handleChange}
                            label="Quadros"
                        >
                        {Object.values(boards).map(({ id, title }) => ( 
                            <MenuItem key={id} value={title}>
                                {title}
                            </MenuItem>
                        ))}
                        </Select>
                    </FormControl>
                </Box>
                <CardPendente id="cardTaskPendente" className="col-md-3 mx-auto my-4">
                    <TypographyTitulo variant="h5">
                                
                    </TypographyTitulo>
                    <TypographyDescricao variant="body2">
                                Descrição da tarefa pendente.
                    </TypographyDescricao>
                </CardPendente>

                <CardEmProgresso id="cardTaskEmProgresso" className="col-md-3 mx-auto my-5">
                    <label>TESTE2</label>
                </CardEmProgresso>

                <CardConcluidos id="cardTaskEmProgresso" className="col-md-3 mx-auto my-5">
                    <label>TESTE2</label>
                </CardConcluidos>
            </div>

        </div>
    )
}



