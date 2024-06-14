import React from "react";
import { useNavigate } from 'react-router-dom';
import { TextField, Button, Card, CardContent, CardMedia, Typography } from '@mui/material/';
import { styled } from '@mui/system';
import { deleteAuthHeaders } from '../adapters/OrdinaryAdapter';

const RoundedTextField = styled(TextField)({
  '& .MuiOutlinedInput-root': {
    borderRadius: '20px',
    backgroundColor: '#0000008a',
    '&:hover fieldset': {
      borderColor: '#002364', // Cor da borda ao passar o mouse
    },
    '&.Mui-focused fieldset': {
      borderColor: '#ffbd59', // Cor da borda quando focado
    },
    '&.Mui-error fieldset': {
      borderColor: '#f44336', // Cor da borda quando há um erro
    },
  },
  '& .MuiInputLabel-root': {
    '&.Mui-focused': {
      color: '#ffbd59', // Cor do rótulo quando focado
    },
    '&.Mui-error': {
      color: '#f44336', // Cor do rótulo quando há um erro
    },
  },
});

const RoundedCard = styled(Card)({
  backgroundColor: "#eeece1",
  borderRadius: '20px'
});

const StyledTypography = styled(Typography)({
  color: 'red', // Cor do texto "VOCÊ SAIU"
});

const StyledButton = styled(Button)({
  backgroundColor: '#00bf63', // Cor do botão
  color: 'black', // Cor do texto do botão
  '&:hover': {
    backgroundColor: '#009e52', // Cor do botão ao passar o mouse
  }
});

export default function Logout() {

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
  }

  deleteAuthHeaders();

  return (
    <div>
      <div className="row">
        <RoundedCard id="cardLogin" className="col-md-4 mx-auto my-5">
          <CardMedia
            component="img"
            className="mx-auto my-2"
            alt="logo taskflow"
            image="/logo2_taskflow.png"
            sx={{ height: 137, width: 154 }}
          />
          <StyledTypography textAlign="center">VOCÊ SAIU</StyledTypography>
          <CardContent>
            <div id="btnRegistrar" className="d-flex justify-content-center">
              <StyledButton
                id="btnRegistrar"
                variant="contained"
                href="/auth/login"
              >
                Faça Login Novamente
              </StyledButton>
            </div>
          </CardContent>
        </RoundedCard>
      </div>
    </div>
  );
}
