<script>
    export default {
        data() {
            return {
                inputs: {
                    username: "",
                    password: ""
                },
                toto: {
                    OK: "Toto get !",
                    KO: "Toto not gotten :(",
                    displayedText: ""
                },
                token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MzIyODk2MDMsInN1YiI6ImFiYyIsImV4cCI6MTc2MzgyMTI3OX0.eJekWrEVeBs_h77WB0tIZzjj-EKT4vbMKw-ODWo23lg"
            }
        },
        methods: {
            async submit() {
                const options = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.inputs)
                };
                const response = await fetch('http://localhost:8080/accounts/login', options);
                if(response.status == 201) {
                    alert('Good Credentials');
                    this.token = await response.body.json();
                    console.log(this.token)
                } else if(response.status == 401) {
                    alert('Bad Credentials');
                } else {
                    console.error('Dev is a failure!');
                }
            },
            async getToto() {
                const options = {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${this.token}`,
                        'Content-Type': 'application/json'
                    }
                };
                const response = await fetch('http://localhost:8080/accounts/toto', options);
                if(response.ok) {
                    this.toto.displayedText = this.toto.OK;
                } else {
                    this.toto.displayedText = this.toto.KO;
                }
            }
        }
    }
</script>

<template>
    <div className="container min-vw-80">
        <h1>{{$t('authentification.Login')}}</h1>
        <form @submit.prevent="submit" novalidate>
            <div className="mb-3">
                <label htmlFor="email" className="form-label">{{$t('authentification.Email address')}}</label>
                <input type="email" name="email" className="form-control" id="email"/>
            </div>
            <div className="mb-3">
                <label htmlFor="password" className="form-label">{{$t('authentification.Password')}}</label>
                <input type="password" name="password" className="form-control" id="password"/>
            </div>
            <button type="submit" disabled={pending} className="btn btn-primary">{{$t('authentification.SubmitButton')}}</button>
        </form>
    </div>
</template>