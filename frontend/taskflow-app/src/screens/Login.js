import React, { useState } from "react";
import Adapter, { post, get } from '../adapters/OrdinaryAdapter';
import Config from '../config.json';
//import Registrar from './Register.js';
import {TextField, Button, Card, CardContent, CardActions, CardMedia } from '@mui/material/';
import { styled } from '@mui/system';

const RoundedTextField = styled(TextField)({
  '& .MuiOutlinedInput-root': {
    borderRadius: '20px',
  }

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
          
        <div className="col-md-12">      
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
                          className="col-md-12"
                          value={email}
                          onChange={(e) => setEmail(e.target.value)}
                          required 
                        />

                        <RoundedTextField
                          id="tfPassword"
                          label="Senha"
                          variant="outlined"
                          className="col-md-12"                          
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
                <div id="divBtnLogin" className="col-md-12">
                  <Button id="btnLogin" className="btn col-3" type="submit" variant="contained">Login</Button>            
                </div>

                <div id="divRegistrar" className="col-md-12">                  
                  <label >Ainda não tem conta? </label> 
                  <Button id="btnRegistrar" variant="text">Registre-se</Button>
                </div>
              </div>

           
              </CardActions>
            </RoundedCard>
          </div>
      </div>





  );
}