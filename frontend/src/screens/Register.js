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
    <div className="container">
        <div className="row">
          <Card className="tf-main-form col-md-4 mx-auto">

            <CardMedia
              component="img"
              className="tf-form-logo mx-auto m2"
              alt="logo TaskFlow"
              image="/logo2_taskflow.png"
            />

            <CardContent>
              {errorMessage && <p className="tf-message tf-message-error">{errorMessage}</p>}         
                            
                <form id="formRegister" onSubmit={handleSubmit}>
                  <div className="form-group">

                      <TextField
                        label="Nome Completo"
                        type="text"
                        variant="outlined"
                        className="col-12"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        margin="dense"
                        required
                        autoComplete="on"
                      />

                      <TextField
                        label="Email"
                        type="email"
                        variant="outlined"
                        className="col-12"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        margin="dense"
                        required
                        autoComplete="on"
                      />

                      <TextField
                        id="field-password"
                        label="Senha"
                        variant="outlined"
                        className="col-12"
                        type={showPassword ? 'text' : 'password'}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        margin="dense"
                        required
                        autoComplete="off"

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
                  
                  <div className="d-flex justify-content-center">
                    <Button 
                    className="col-5 mt-3" 
                    type="submit"
                    variant="contained">
                      Registrar
                    </Button>
                  </div>
                </form>
            </CardContent>

            <CardActions>
              <div  className="col-12 d-flex justify-content-center">                  
                <Typography className="tf-form-footer">Já possui uma conta?</Typography>
                <Button 
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