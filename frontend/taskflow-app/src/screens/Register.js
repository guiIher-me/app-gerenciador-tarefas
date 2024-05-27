import React, { useState } from "react";
import Config from '../config.json';
import Adapter, { post } from '../adapters/OrdinaryAdapter';
import { useNavigate } from "react-router-dom";
import {TextField, Button, Card, CardContent, CardMedia} from '@mui/material/';
import { styled } from '@mui/system';

const RoundedTextField = styled(TextField)({
  '& .MuiOutlinedInput-root': {
    borderRadius: '20px',
    backgroundColor:'#0000008a',
    margin:'5px',
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

export default function Register() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
        "name": name,
        "login": email,
        "password": password,
        "role": "USER"
    }

    try {
      const response = await post(Config.apiURL+'auth/register', requestBody)
      const { token } = response.data;
      localStorage.setItem("token", token);
      navigate('/');
      
    } catch (error) {
      setErrorMessage("Erro ao salvar registro!");
    }
  };

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
              <CardContent>
                {errorMessage && <p>{errorMessage}</p>}         
                              
                  <form id="formLogin" onSubmit={handleSubmit}>
                    <div className="form-group">

                        <RoundedTextField 
                          id="tfName" 
                          label="Nome Completo" 
                          variant="outlined"
                          className="col-12"
                          value={name}
                          onChange={(e) => setName(e.target.value)}
                          required 
                        />

                        <RoundedTextField 
                          id="tfEmail" 
                          label="Email" 
                          variant="outlined"
                          className="col-12"
                          value={email}
                          onChange={(e) => setEmail(e.target.value)}
                          required 
                        />

                        <RoundedTextField
                          id="tfPassword"
                          label="Senha"
                          variant="outlined"
                          className="col-12"                          
                          value={password}
                          onChange={(e) => setPassword(e.target.value)}
                          margin="dense"
                          required
                        />                  

                    </div>
                    <div id="divBtnRegister" className="d-flex justify-content-center">
                      <Button 
                      id="btnRegister" 
                      className="col-5" 
                      type="submit" 
                      variant="contained"
                      sx={{color: 'black',
                          backgroundColor: '#002364',
                          margin:'5px', 
                      '&:hover': {
                        backgroundColor: '#00bf63', // Cor de fundo ao passar o mouse
                      },
                      }}>
                        Registrar-me</Button>            
                    </div>
                  </form>
              </CardContent>
            </RoundedCard>
          </div>
      </div>
  );
}