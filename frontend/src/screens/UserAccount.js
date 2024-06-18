import React, { useState, useEffect } from "react";
import Config from '../config.json';
import { useNavigate } from "react-router-dom";
import { TextField, Button, Card, CardContent, IconButton, InputAdornment } from '@mui/material/';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import Header from '../components/Header.js';

import { HttpRequestAuth } from "../http";
import { UnauthenticatedError } from "../exceptions/UnauthenticatedError.js";
const httpAuth = new HttpRequestAuth();

export default function UserAccount() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");
    const [showOldPassword, setshowOldPassword] = useState(false);
    const [showNewPassword, setshowNewPassword] = useState(false);
    const [message, setMessage] = useState({text: "", type: null});

    const navigate = useNavigate();

    const resetFields = () => {
        setOldPassword("");
        setNewPassword("");
        setConfirmNewPassword("");
    }

    useEffect(() => {
        async function getUser() {
            try {
                const response = await httpAuth.get(`${Config.BASEPATH}/user/`);
                const { name, email } = response.data;
                setName(name);
                setEmail(email);
            } catch (error) {
                if (error instanceof UnauthenticatedError)
                    navigate("/auth/login");
                
                setMessage({ text: Config.MESSAGES.UNEXPECTED_ERROR, type: "error" });
            }
        }
        getUser();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage({text: "", type: null});

        if ((oldPassword !== "" ^ newPassword !== "")
                || newPassword !== "" ^ confirmNewPassword !== "") {
            setMessage({ text: "Preencha todos os campos de senha!", type: "error" });
            return;
        }

        if (newPassword !== confirmNewPassword) {
            setMessage({ text: "As senhas não coincidem!", type: "error" });
            return;
        }

        if (oldPassword === newPassword && oldPassword !== "") {
            setMessage({ text: "A senha antiga e a nova não devem ser iguais!", type: "error" });
            return;
        }
    
        const requestBody = {
          "name": name !== "" ? name : undefined,
          "oldPassword": oldPassword !== "" ? oldPassword : undefined,
          "newPassword": newPassword !== "" ? newPassword : undefined
        }

        try {
            console.log("requesting...")
            const response = await httpAuth.patch(`${Config.BASEPATH}/user/`, requestBody);
            const { name } = response.data;
            resetFields();
            setName(name);
            setMessage({ text: "Dados alterados com sucesso!", type: "success" });
        } catch(error) {
            console.log(error);
            setMessage({ text: error.response.data.message, type: "error" });
        }
    }

    return (
        <div>
            <Header />

            <div className="container">       
                <div className="row">
                    <Card className="tf-main-form col-md-4 mx-auto">
                        <h1 className="text-center">Dados da Conta</h1>

                        <CardContent>
                            {message.type && <p className={`tf-message tf-message-${message.type}`}>{message.text}</p>}

                            <form id="tf-form-user-account" onSubmit={handleSubmit}>
                                <div className="form-group">

                                    <TextField
                                        label="Email"
                                        type="email"
                                        variant="outlined"
                                        className="col-12"
                                        value={email}
                                        margin="dense"
                                        disabled={true}
                                    />

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
                                        label="Senha Antiga"
                                        variant="outlined"
                                        className="col-12"
                                        value={oldPassword}
                                        type={showOldPassword ? 'text' : 'password'}
                                        onChange={(e) => setOldPassword(e.target.value)}
                                        margin="dense"
                                        autoComplete="off"

                                        InputProps={{
                                            endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                aria-label="toggle password visibility"
                                                onClick={() => { setshowOldPassword(!showOldPassword) }}
                                                edge="end"
                                                >
                                                {showOldPassword ? <VisibilityOff /> : <Visibility />}
                                                </IconButton>
                                            </InputAdornment>
                                            )
                                        }}
                                    />

                                    <TextField
                                        label="Senha Nova"
                                        variant="outlined"
                                        className="col-12"
                                        value={newPassword}
                                        type={showNewPassword ? 'text' : 'password'}
                                        onChange={(e) => setNewPassword(e.target.value)}
                                        margin="dense"
                                        autoComplete="off"

                                        InputProps={{
                                            endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                aria-label="toggle password visibility"
                                                onClick={() => { setshowNewPassword(!showNewPassword) }}
                                                edge="end"
                                                >
                                                {showNewPassword ? <VisibilityOff /> : <Visibility />}
                                                </IconButton>
                                            </InputAdornment>
                                            )
                                        }}
                                    />

                                    <TextField
                                        label="Repita a Senha Nova"
                                        variant="outlined"
                                        className="col-12"
                                        value={confirmNewPassword}
                                        type={showNewPassword ? 'text' : 'password'}
                                        onChange={(e) => setConfirmNewPassword(e.target.value)}
                                        margin="dense"
                                        autoComplete="off"
                                    />
                                </div>
                                
                                <div className="d-flex justify-content-center">
                                    <Button 
                                    className="col-3 mt-3" 
                                    type="submit"
                                    variant="contained"
                                    >
                                    ALterar
                                    </Button>
                                </div>
                            </form>
                        </CardContent>
                    </Card>
                </div>
            </div>
        </div>
    );
}