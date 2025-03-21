<script>
    import { useVuelidate } from '@vuelidate/core'
    import { helpers, maxLength, minLength, required } from '@vuelidate/validators'
    import api from '../api.js'

    const passwordRegex = helpers.regex(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[;:\*\-!]).{8,}$/)
    const emailRegex = helpers.regex(/^(?=.{1,64}@)\w+([\.-]?\w+)*@(?=.{4,252}$)\w+([\.-]?\w+)*(\.\w{2,4})+$/)
    // check the email in the server
    // const isEmailTaken = (value) => fetch(`/api/unique/${value}`).then(r => r.json()) 

    export default {
        setup() {
            return {
                v$: useVuelidate()
            }
        },
        data() {
            return {
                inputs: {
                    email: "",
                    password: ""
                }
            }
        },
        validations() {
            return {
                inputs: {
                    email: { 
                        required,
                        maxLength: maxLength(255),
                        emailRegex
                    },
                    password: { 
                        required,
                        minLength: minLength(8),
                        passwordRegex 
                    }
                }
            }
        },
        methods: {
            async submit() {
                const isFormCorrect = await this.v$.$validate();
                if(!isFormCorrect) return;

                api.post('/user', {
                    email: 'qweewq2@qwe.com',
                    password: 'qweqwe1!',
                    username: 'qwe'
                }).then(function (response) {
                    console.log("then " + response);
                }).catch(function (error) {
                    if (error.response) {
                        // The request was made and the server responded with a status code
                        // that falls out of the range of 2xx
                        console.log(error.response.data);
                        console.log(error.response.status);
                        console.log(error.response.headers);
                    } else if (error.request) {
                        // The request was made but no response was received
                        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                        // http.ClientRequest in node.js
                        console.log("error " + error.request);
                    } else {
                        // Something happened in setting up the request that triggered an Error
                        console.log('Error', error.message);
                    }
                    console.log("last " + error.config);
                });
            },

        }
    }
</script>

<template>
    <div className="container min-vw-80">
        <h1>{{$t('authentification.Register')}}</h1>
        <form @submit.prevent="submit" novalidate>
            <div className="mb-3">
                <label htmlFor="email" className="form-label">{{$t('authentification.EmailAddress')}}</label>
                <input v-model="inputs.email" type="email" name="email" className="form-control" id="email"/>
                <div class="text-danger" v-if="v$.inputs.email.$error">{{ $t('authentification.EmailError') }}</div>
            </div>
            <div className="mb-3">
                <label htmlFor="password" className="form-label">{{$t('authentification.Password')}}</label>
                <input v-model="inputs.password" type="password" name="password" className="form-control" id="password"/>
                <div class="text-danger" v-if="v$.inputs.password.$error">{{ $t('authentification.PasswordError') }}</div>
            </div>
            <button type="submit" className="btn btn-primary">{{$t('authentification.SubmitButton')}}</button>
        </form>
    </div>
</template>