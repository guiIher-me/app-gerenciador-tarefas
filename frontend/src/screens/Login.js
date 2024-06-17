import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { TextField, Button, Card, CardContent, CardActions, CardMedia, Typography, IconButton, InputAdornment } from '@mui/material/';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import Config from '../config.json';

import { HttpRequest, HttpAuthToken } from "../http";
const http = new HttpRequest();

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      "login": email,
      "password": password
    }

    try {
      const response = await http.post(`${Config.BASEPATH}/auth/login`, requestBody);
      const { token } = response.data;
      HttpAuthToken.setToken(token);
      navigate('/Home');
    } catch (error) {
      const { status } = error.response;
      if (status === 401) setErrorMessage(Config.MESSAGES.INVALID_LOGIN);
      else setErrorMessage(Config.MESSAGES.INTERNAL_SERVER_ERROR);
    }
  };

  const handleClickShowPassword = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div>       
      <div className="row">      
        <Card id="cardLogin" className="col-md-4 mx-auto">
          <CardMedia
            component="img"
            className="mx-auto my-2"
            alt="logo taskflow"
            image="/logo2_taskflow.png"
            sx={{ height: 100, width: "auto" }}
          />
          <CardContent>
            {errorMessage && <p>{errorMessage}</p>}  
            <form id="formLogin" onSubmit={handleSubmit}>
              <div className="form-group">
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
              <div id="divBtnLogin" className="d-flex justify-content-center">
                <Button 
                  id="btnLogin" 
                  className="col-3" 
                  type="submit" 
                  variant="contained"
                >
                  Login
                </Button>            
              </div>
            </form>
          </CardContent>
          <CardActions>
            <div id="divRegistrar" className="col-12 d-flex justify-content-center">                  
              <Typography color={'black'} margin={'6px'}>Ainda não tem uma conta?</Typography>
              <Button 
                id="btnRegistrar" 
                variant="text"
                href="/auth/register"
              >
                Registre-se!
              </Button>
            </div>
          </CardActions>
        </Card>
      </div>
    </div>
  );
}
