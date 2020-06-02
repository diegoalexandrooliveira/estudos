import { BasicStrategy } from 'passport-http';

const express = require('express');
const passport = require('passport');
const path = require('path');

passport.use(new BasicStrategy(
    function (usuario, senha, done) {
        if (!usuario) { return done(null, false); }
        if (!verificaUsuario(usuario)) { return done(null, false); }
        if (!verificaSenha(senha)) { return done(null, false); }
        return done(null, true);
    }
));

const app = express();


app.use('/download', passport.authenticate('basic', { session: false }));
app.use('/download', express.static(path.join(__dirname, '..', '/download')));

app.listen(8080, () => {
    console.log('Aplicação rodando na porta 8080');
});


function verificaSenha(password) {
    if (password !== process.env.SENHA) {
        return false;
    }
    return true;
}

function verificaUsuario(password) {
    if (password !== process.env.USUARIO) {
        return false;
    }
    return true;
}


