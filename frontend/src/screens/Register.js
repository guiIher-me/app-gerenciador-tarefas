import React, { useState } from "react";
import Config from '../config.json';
import { useNavigate } from "react-router-dom";
import { TextField, Button, Card, CardContent, CardActions, CardMedia, Typography, IconButton, InputAdornment } from '@mui/material/';
import { Visibility, VisibilityOff } from '@mui/icons-material';

import { HttpRequest, HttpAuthToken } from "../http";
const http = new HttpRequest();

export default function Register() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

  const handleClickShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
        "name": name,
        "login": email,
        "password": password,
        "role": "USER"
    }

    try {
      const response = await http.post(`${Config.BASEPATH}/auth/register`, requestBody)
      const { token } = response.data;
      HttpAuthToken.setToken(token);
      navigate('/');
      
    } catch (error) {
      const { status } = error.response;
      if (status === 500) {
        setErrorMessage(Config.MESSAGES.INTERNAL_SERVER_ERROR);
        return;
      }

      setErrorMessage(error.response.data.message);
    }
  };

  return (
    <div>
        <div className="row">      
            <Card id="cardLogin" className="col-md-4 mx-auto my-5">
            <CardMedia
              component="img"
              className="mx-auto my-2"
              alt="logo taskflow"
              image="/logo2_taskflow.png"
              sx={{ height: 100, width: "auto" }}
            />
              <CardContent>
                {errorMessage && <p style={{"color": "black"}}>{errorMessage}</p>}         
                              
                  <form id="formLogin" onSubmit={handleSubmit}>
                    <div className="form-group">

                        <TextField 
                          id="tfName" 
                          label="Nome Completo" 
                          variant="outlined"
                          className="col-12"
                          value={name}
                          onChange={(e) => setName(e.target.value)}
                          margin="dense"
                          required 
                        />

                        <TextField 
                          id="tfEmail" 
                          label="Email"
                          type="email"
                          variant="outlined"
                          className="col-12"
                          value={email}
                          onChange={(e) => setEmail(e.target.value)}
                          margin="dense"
                          required 
                        />

                        <TextField
                          id="tfPassword"
                          label="Senha"
                          variant="outlined"
                          className="col-12"
                          type={showPassword ? 'text' : 'password'}
                          value={password}
                          onChange={(e) => setPassword(e.target.value)}
                          margin="dense"
                          required

                          InputProps={{
                            endAdornment: (
                              <InputAdornment position="end">
                                <IconButton
                                  aria-label="toggle password visibility"
                                  onClick={handleClickShowPassword}
                                  edge="end"
                                >
                                  {showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                              </InputAdornment>
                            )
                          }}
                        />    
                    </div>
                    
                    <div id="divBtnRegister" className="d-flex justify-content-center">
                      <Button 
                      id="btnRegister" 
                      className="col-5" 
                      type="submit" 
                      variant="contained">
                        Registrar
                      </Button>            
                    </div>
                  </form>
              </CardContent>
              <CardActions>
            <div id="divRegistrar" className="col-12 d-flex justify-content-center">                  
              <Typography color={'black'} margin={'6px'}>Já possui uma conta?</Typography>
              <Button 
                id="btnRegistrar" 
                variant="text"
                href="/auth/login"
              >
                Realize o Login!
              </Button>
            </div>
          </CardActions>
            </Card>
          </div>
      </div>
  );
}