import React from 'react';
import { TextField, Card, Stack, IconButton } from '@mui/material/';
import { styled } from '@mui/system';
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

    const handleBlurOrEnter = (listId, cardIndex, field, value) => {
        handleProgressoChange(listId, cardIndex, field, value);
    };

    const handleKeyDown = (e, listId, cardIndex, field, value) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            handleBlurOrEnter(listId, cardIndex, field, value);
        }
    };

    return (
        <>
            {tasks && tasks.map((task, taskIndex) => (
                <CardEmProgresso key={taskIndex}>
                    <DeleteButton onClick={() => handleDeleteTask(listId, taskIndex)}>
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
        </>
    );
}
