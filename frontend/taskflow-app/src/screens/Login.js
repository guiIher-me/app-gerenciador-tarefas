import React, { useState } from "react";
import Adapter, { post, get } from '../adapters/OrdinaryAdapter';
import Config from '../config.json';
import Register from './Register.js';
import {TextField, Button, Card, CardContent, CardActions, CardMedia, Typography, colors } from '@mui/material/';
import { styled } from '@mui/system';


const RoundedTextField = styled(TextField)({
  '& .MuiOutlinedInput-root': {
    borderRadius: '20px',
    backgroundColor:'#0000008a',
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

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      "login": email,
      "password": password
    }

    try {
      const response = await post(Config.apiURL+'auth/login', requestBody)
      const { token } = response.data;
      localStorage.setItem("token", token);

    } catch (error) {
      setErrorMessage("Email ou senha inválidos");
    }
  };

  return (
    <div>       
          
        <div className="row">      
            <RoundedCard id="cardLogin" className="col-md-4 mx-auto my-5" >
            <CardMedia
              component="img"
              className="mx-auto my-2"
              alt="logo taskflow"
              image="/logo2_taskflow.png"
              sx={{ height: 137, width: 154 }}
            />
              {/* <img src="/logo_taskflow.png"></img> */}
              <CardContent>
                {/* <label>Login</label> */}
                {errorMessage && <p>{errorMessage}</p>}         
                              
                  <form id="formLogin" onSubmit={handleSubmit}>
                    <div className="form-group">
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
                  </form>

              </CardContent>

              <CardActions>

              <div className="row">
                <div id="divBtnLogin" className="d-flex justify-content-center">
                  <Button 
                  id="btnLogin" 
                  className="col-3" 
                  type="submit" 
                  variant="contained"
                  sx={{color: 'black',
                      backgroundColor: '#00bf63', // Cor de fundo
                  '&:hover': {
                    backgroundColor: '#002364', // Cor de fundo ao passar o mouse
                  },
                  }}>
                    Login</Button>            
                </div>

                <div id="divRegistrar" className="d-flex justify-content-center">                  
                  {/* <label >Ainda não tem conta? </label>  */}
                  <Typography color={'black'} margin={'6px'}>Ainda não tem conta?</Typography>
                  <Button 
                    id="btnRegistrar" 
                    variant="text"
                    href="/auth/register"
                    sx={{color: '#002364'
                  }}>
                    Registre-se</Button>
                </div>
              </div>
           
              </CardActions>
            </RoundedCard>
          </div>
      </div>





  );
}