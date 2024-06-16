import React, { useEffect, useState } from 'react';
import { TextField, Card, Stack, IconButton, Autocomplete, CircularProgress, Button } from '@mui/material/';
import { styled } from '@mui/system';
import Config from '../config.json';
import Adapter, { get, post, del } from '../adapters/OrdinaryAdapter';
import { createSvgIcon } from '@mui/material/utils';
import DeleteIcon from '@mui/icons-material/Delete';

const CardEmProgresso = styled(Card)({
    backgroundColor: "#ffbd59",
    borderRadius: '5px',
    padding: '16px',
    marginTop: '16px',
    position: 'relative'
});

const DeleteButton = styled(IconButton)({
    position: 'absolute',
    top: '8px',
    right: '8px',
    color: '#000',
    '&:hover': {
        backgroundColor: 'transparent'
    },
    '&:hover .MuiIconButton-label': {
        color: 'red'
    }
});

const TextFieldBlackText = styled(TextField)({
    '& .MuiInputBase-input': {
        color: '#000'
    },
    '& .MuiInputBase-root.Mui-disabled': {
        color: '#000',
    }
});

export default function Tasks({ tasks, handleProgressoChange, handleDeleteTask, listId }) {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);

    //renderiza os usuários no combo box. useEffect garante a busca dos users logo após o componente ser montado.
    useEffect(() => {
        const fetchUsers = async () => {
            setLoading(true);
            try {
                const response = await get(Config.apiURL + 'user/');
                setUsers(response.data);
            } catch (error) {
                console.error('Erro ao buscar usuários:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchUsers();
    }, []);

    const handleUserChange = (event, value, taskIndex) => {
        const userIds = value.map(user => user.id);
        handleProgressoChange(listId, taskIndex, 'usersIds', userIds);
    };

    const handleBlurOrEnter = (listId, cardIndex, field, value) => {
        handleProgressoChange(listId, cardIndex, field, value);
    };

    const handleKeyDown = (e, listId, cardIndex, field, value) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            handleBlurOrEnter(listId, cardIndex, field, value);
        }
    };

    const ButtonCriarTarefa = styled(Button)({
        margin: '15px',
    });

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

    return (
        <div>
            {tasks && tasks.map((task, taskIndex) => (
                <CardEmProgresso key={taskIndex} id={task.id}>

                    <DeleteButton onClick={() => handleDeleteTask(listId, taskIndex, task.id)}>
                        <DeleteIcon />
                    </DeleteButton>

                    <TextFieldBlackText
                        variant="standard"
                        fullWidth
                        value={task.title}
                        onChange={(e) => handleProgressoChange(listId, taskIndex, "title", e.target.value)}
                        onBlur={(e) => handleBlurOrEnter(listId, taskIndex, "title", e.target.value)}
                        onKeyDown={(e) => handleKeyDown(e, listId, taskIndex, "title", e.target.value)}
                        placeholder="Título"
                        disabled={task.isSaved}
                        InputProps={{
                            style: { color: '#000' }
                        }}
                        InputLabelProps={{
                            style: { color: '#000' }
                        }}
                    />
                    <TextFieldBlackText
                        variant="outlined"
                        fullWidth
                        multiline
                        rows={4}
                        value={task.description}
                        onChange={(e) => handleProgressoChange(listId, taskIndex, "description", e.target.value)}
                        onBlur={(e) => handleBlurOrEnter(listId, taskIndex, "description", e.target.value)}
                        onKeyDown={(e) => handleKeyDown(e, listId, taskIndex, "description", e.target.value)}
                        placeholder="Descrição"
                        style={{ marginTop: '8px' }}
                        disabled={task.isSaved}
                        InputProps={{
                            style: { color: '#000' }
                        }}
                        InputLabelProps={{
                            style: { color: '#000' }
                        }}
                    />
                    {task.isSaved ? (
                        <TextFieldBlackText
                            variant="outlined"
                            fullWidth
                            value={Array.isArray(task.usersNames) ? task.usersNames.join(', ') : ''} // Ensure usersNames is an array
                            InputProps={{
                                readOnly: true,
                                style: { color: '#000' }
                            }}
                            style={{ marginTop: '8px' }}
                            placeholder="Usuários"
                        />
                    ) : (
                        <Autocomplete
                            multiple
                            options={users}
                            getOptionLabel={(option) => option.name}
                            value={users.filter(user => task.usersIds && task.usersIds.includes(user.id))}
                            onChange={(event, value) => handleUserChange(event, value, taskIndex)}
                            loading={loading}
                            getOptionKey={(option) => option.id}
                            renderInput={(params) => (
                                <TextFieldBlackText
                                    {...params}
                                    variant="outlined"
                                    label="Usuários"
                                    placeholder="Selecione os usuários"
                                    InputProps={{
                                        ...params.InputProps,
                                        endAdornment: (
                                            <>
                                                {loading ? <CircularProgress color="inherit" size={20} /> : null}
                                                {params.InputProps.endAdornment}
                                            </>
                                        ),
                                    }}
                                />
                            )}
                            style={{ marginTop: '8px' }}
                        />
                    )}

                    <Stack direction="row" spacing={2} style={{ marginTop: '8px' }}>
                        <TextFieldBlackText
                            variant="outlined"
                            type="date"
                            fullWidth
                            value={task.startDate}
                            onChange={(e) => handleProgressoChange(listId, taskIndex, "startDate", e.target.value)}
                            onBlur={(e) => handleBlurOrEnter(listId, taskIndex, "startDate", e.target.value)}
                            onKeyDown={(e) => handleKeyDown(e, listId, taskIndex, "startDate", e.target.value)}
                            label="Data Início"
                            disabled={task.isSaved}
                            InputLabelProps={{ shrink: true, style: { color: '#000' } }}
                            InputProps={{
                                style: { color: '#000' }
                            }}
                        />
                        <TextFieldBlackText
                            variant="outlined"
                            type="date"
                            fullWidth
                            value={task.endDate}
                            onChange={(e) => handleProgressoChange(listId, taskIndex, "endDate", e.target.value)}
                            onBlur={(e) => handleBlurOrEnter(listId, taskIndex, "endDate", e.target.value)}
                            onKeyDown={(e) => handleKeyDown(e, listId, taskIndex, "endDate", e.target.value)}
                            label="Data Fim"
                            disabled={task.isSaved}
                            InputLabelProps={{ shrink: true, style: { color: '#000' } }}
                            InputProps={{
                                style: { color: '#000' }
                            }}
                        />
                    </Stack>
                </CardEmProgresso>
            ))}
        </div>
    );
}